package com.francesco.javaLab.testUtils;

import com.francesco.javaLab.entity.EmployeeEntity;
import com.francesco.javaLab.model.input.EmployeeInputModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;


public class EmployeeUtils {
  public static EmployeeEntity generateEmployeeEntity(Long employeeId, EmployeeInputModel employeeInputModel) {
    EmployeeEntity employeeEntity = new EmployeeEntity();
    employeeEntity.setId(employeeId);
    if(employeeInputModel == null) {
      employeeEntity.setName(RandomStringUtils.randomAlphabetic(20));
      employeeEntity.setIdentificationNumber(RandomStringUtils.randomAlphanumeric(15));
      employeeEntity.setCompanyRole(RandomStringUtils.randomAlphabetic(10));
    } else {
      employeeEntity.setName(employeeInputModel.getName());
      employeeEntity.setIdentificationNumber(employeeInputModel.getIdentificationNumber());
      employeeEntity.setCompanyRole(employeeInputModel.getCompanyRole());
    }

    return employeeEntity;
  }

  public static EmployeeInputModel generateEmployeeInputModel() {
    EmployeeInputModel employeeInputModel = new EmployeeInputModel();
    employeeInputModel.setName(RandomStringUtils.randomAlphabetic(20));
    employeeInputModel.setIdentificationNumber(RandomStringUtils.randomAlphanumeric(15));
    employeeInputModel.setCompanyRole(RandomStringUtils.randomAlphabetic(10));
    employeeInputModel.setLocationId(new Random().nextLong());
    return employeeInputModel;
  }
}
