package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class RentalOutputModel {

  private Long id;

  private Date startDate;

  private Date endDate;

  private Boolean isFinished;

  private VehicleOutputModel vehicle;

  private ClientOutputModel client;

  private ReviewOutputModel review;

}
