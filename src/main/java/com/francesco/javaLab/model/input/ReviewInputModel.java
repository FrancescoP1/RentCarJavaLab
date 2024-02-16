package com.francesco.javaLab.model.input;

import com.francesco.javaLab.constants.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInputModel {
  @NotBlank(message = ValidationMessages.REVIEW_COMMENT_NOT_BLANK)
  @Size(max = 1000, message = ValidationMessages.REVIEW_COMMENT_SIZE)
  private String comment;

  @NotNull(message = ValidationMessages.REVIEW_RATING_NOT_NULL)
  @Positive(message = ValidationMessages.REVIEW_RATING_POSITIVE)
  private Integer rating;

  @NotNull(message = ValidationMessages.REVIEW_RENTAL_ID_NOT_NULL)
  @Positive(message = ValidationMessages.REVIEW_RENTAL_ID_POSITIVE)
  private Long rentalId;
}
