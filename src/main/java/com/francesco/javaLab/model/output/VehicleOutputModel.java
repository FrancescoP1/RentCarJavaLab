package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VehicleOutputModel {

  private Long id;

  private String brand;

  private String model;

  private Integer modelYear;

  private String vin;

  private Integer odometerReading;

  private LocationOutputModel location;
}
