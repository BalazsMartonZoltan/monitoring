package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    @Query("""
        select avg(sr.value)
        from SensorReading sr
        where sr.sensor.type = :type
        and sr.timestamp between :from and :to
        """)
    Optional<Double> findAverageBySensorTypeAndTimestampBetween(
            @Param("type") SensorType type,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to);

    List<SensorReading> findBySensor_TypeAndTimestampBetweenOrderByTimestamp(SensorType type, LocalDateTime from, LocalDateTime to);

}
