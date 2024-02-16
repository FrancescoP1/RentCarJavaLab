package com.francesco.javaLab.model.input;

import com.francesco.javaLab.constants.ValidationMessages;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalInputModel {

  @NotNull(message = ValidationMessages.RENTAL_START_DATE_NOT_NULL)
  @PastOrPresent(message = ValidationMessages.RENTAL_START_DATE_PAST_OR_PRESENT)
  private Date startDate;

  @NotNull(message = ValidationMessages.RENTAL_END_DATE_NOT_NULL)
  @FutureOrPresent(message = ValidationMessages.RENTAL_END_DATE_FUTURE_OR_PRESENT)
  private Date endDate;

  @NotNull(message = ValidationMessages.RENTAL_VEHICLE_ID_NOT_NULL)
  @Positive(message = ValidationMessages.RENTAL_VEHICLE_ID_POSITIVE)
  private Long vehicleId;

  @NotNull(message = ValidationMessages.RENTAL_CLIENT_ID_NOT_NULL)
  @Positive(message = ValidationMessages.RENTAL_CLIENT_ID_POSITIVE)
  private Long clientId;
}
