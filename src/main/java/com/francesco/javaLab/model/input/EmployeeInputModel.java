package com.francesco.javaLab.model.input;

import com.francesco.javaLab.constants.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInputModel {

  @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
  @Size(min = 3, max = 50, message = ValidationMessages.NAME_SIZE)
  private String name;

  @NotBlank(message = ValidationMessages.IDENTIFICATION_NUMBER_NOT_BLANK)
  @Size(min = 5, max = 15, message = ValidationMessages.IDENTIFICATION_NUMBER_SIZE)
  private String identificationNumber;

  @NotBlank(message = ValidationMessages.EMPLOYEE_COMPANY_ROLE_NOT_BLANK)
  @Size(min = 2, max = 30, message = ValidationMessages.EMPLOYEE_COMPANY_ROLE_SIZE)
  private String companyRole;

  @NotNull(message = ValidationMessages.EMPLOYEE_LOCATION_ID_NOT_NULL)
  private Long locationId;
}
