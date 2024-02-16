package com.francesco.javaLab.model.input;

import com.francesco.javaLab.constants.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInputModel {

  @NotBlank(message = ValidationMessages.VEHICLE_BRAND_NOT_BLANK)
  @Size(min = 2, max = 50, message = ValidationMessages.VEHICLE_BRAND_SIZE)
  private String brand;

  @NotBlank(message = ValidationMessages.VEHICLE_MODEL_NOT_BLANK)
  @Size(min = 2, max = 50, message = ValidationMessages.VEHICLE_MODEL_SIZE)
  private String model;

  @NotNull(message = ValidationMessages.VEHICLE_MODEL_YEAR_NOT_BLANK)
  @Range(min = 1900, max = 2024, message = ValidationMessages.VEHICLE_MODEL_YEAR_NOT_BLANK)
  private Integer modelYear;

  @NotBlank(message = ValidationMessages.VEHICLE_VIN_NOT_BLANK)
  @Size(min = 17, max = 17, message = ValidationMessages.VEHICLE_VIN_SIZE)
  private String vin;

  @NotNull(message = ValidationMessages.VEHICLE_ODOMETER_READING_NOT_NULL)
  private Integer odometerReading;

  @NotNull(message = ValidationMessages.VEHICLE_LOCATION_ID_NOT_NULL)
  private Long locationId;
}
