package com.francesco.javaLab.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewOutputModel {

  private Long id;

  private String comment;

  private Integer rating;

  private RentalOutputModel rental;
}
