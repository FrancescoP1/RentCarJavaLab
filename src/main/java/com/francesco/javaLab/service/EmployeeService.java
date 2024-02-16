package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.EmployeeEntity;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.mapper.EmployeeEntityMapper;
import com.francesco.javaLab.model.input.EmployeeInputModel;
import com.francesco.javaLab.model.output.EmployeeOutputModel;
import com.francesco.javaLab.repository.EmployeeRepository;
import com.francesco.javaLab.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final EmployeeEntityMapper employeeEntityMapper = EmployeeEntityMapper.INSTANCE;
  private final LocationRepository locationRepository;

  @Transactional
  public EmployeeOutputModel addEmployee(EmployeeInputModel employeeInputModel) {
    if(!locationRepository.existsById(employeeInputModel.getLocationId())) {
      throw new ResourceNotFoundException(ExceptionConstants.LOCATION_NOT_FOUND);
    }
    EmployeeEntity employeeEntity =
        employeeEntityMapper.fromInputModelToEntity(employeeInputModel);
    employeeEntity = employeeRepository.save(employeeEntity);
    return employeeEntityMapper.fromEntityToOutputModel(employeeEntity);
  }

  @Transactional
  public Boolean removeEmployee(Long employeeId) {
    if(employeeRepository.existsById(employeeId)) {
      employeeRepository.deleteById(employeeId);
      return true;
    }
    throw new ResourceNotFoundException(ExceptionConstants.EMPLOYEE_NOT_FOUND);
  }

  public List<EmployeeOutputModel> getAllEmployees(Long locationId) {
    if(locationId != null) {
      return employeeEntityMapper
          .fromEntitiesToOutputModels(
              employeeRepository.findAllByLocationId(locationId));
    }
    return employeeEntityMapper
        .fromEntitiesToOutputModels(employeeRepository.findAll());
  }

}
