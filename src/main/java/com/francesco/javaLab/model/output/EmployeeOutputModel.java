package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeOutputModel {

  private Long id;

  private String name;

  private String identificationNumber;

  private String companyRole;

  private LocationOutputModel location;
}
