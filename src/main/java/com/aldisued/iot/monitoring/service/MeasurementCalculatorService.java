package com.aldisued.iot.monitoring.service;


import java.util.List;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {

    if(deviation > 1.0 || deviation < 0.0) {
      throw new IllegalArgumentException();
    }

    OptionalDouble optionalAverage =
            values.stream()
                    .mapToDouble(Double::doubleValue)
                    .average();

    if(optionalAverage.isEmpty()){
      return List.of();
    }
    double average = optionalAverage.getAsDouble();
    double tolerance = average * deviation;
    double lowerLimit = average - tolerance;
    double upperLimit = average + tolerance;
    return values.stream().filter(value -> value > lowerLimit && value < upperLimit).toList();
  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

}
