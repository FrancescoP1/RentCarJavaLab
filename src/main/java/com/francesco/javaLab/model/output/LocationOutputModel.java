package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationOutputModel {

  private Long id;

  private String locationName;

  private String city;
}
