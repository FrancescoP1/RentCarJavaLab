package com.francesco.javaLab.model.input;

import com.francesco.javaLab.constants.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationInputModel {

  @NotBlank(message = ValidationMessages.LOCATION_LOCATION_NAME_NOT_BLANK)
  @Size(min = 2, max = 50, message = ValidationMessages.LOCATION_LOCATION_NAME_SIZE)
  private String locationName;

  @NotBlank(message = ValidationMessages.LOCATION_CITY_NOT_BLANK)
  @Size(min = 2, max = 50, message = ValidationMessages.LOCATION_CITY_SIZE)
  private String city;

}
