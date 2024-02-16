package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientOutputModel {

  private Long id;

  private String name;

  private String identificationNumber;
}
