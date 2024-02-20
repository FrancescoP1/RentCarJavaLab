package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.EmployeeEntity;
import com.francesco.javaLab.exception.ActionNotAllowedException;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.model.input.EmployeeInputModel;
import com.francesco.javaLab.model.output.EmployeeOutputModel;
import com.francesco.javaLab.repository.EmployeeRepository;
import com.francesco.javaLab.repository.LocationRepository;
import com.francesco.javaLab.testUtils.EmployeeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
  @Mock
  private EmployeeRepository employeeRepositoryMock;
  @Mock
  private LocationRepository locationRepositoryMock;

  @InjectMocks
  private EmployeeService employeeService;

  private Long employeeId;
  private List<EmployeeEntity> employees;

  @BeforeEach
  public void setup() {
    employeeId = 10L;
    employees = new ArrayList<>(Arrays.asList(
        EmployeeUtils.generateEmployeeEntity(employeeId, null),
        EmployeeUtils.generateEmployeeEntity(20L, null)
    ));
  }

  @Test
  public void addEmployeeTest() {
    EmployeeInputModel employeeInputModel =
        EmployeeUtils.generateEmployeeInputModel();
    EmployeeEntity employeeEntity =
        EmployeeUtils.generateEmployeeEntity(employeeId, employeeInputModel);
    when(locationRepositoryMock.existsById(employeeInputModel.getLocationId()))
        .thenReturn(true);
    when(employeeRepositoryMock.existsByIdentificationNumber(employeeInputModel.getIdentificationNumber()))
        .thenReturn(false);
    when(employeeRepositoryMock.save(any())).thenReturn(employeeEntity);
    EmployeeOutputModel employeeOutputModel =
        employeeService.addEmployee(employeeInputModel);
    assertAll(
        () -> assertThat(employeeOutputModel.getId()).isEqualTo(employeeEntity.getId()),
        () -> assertThat(employeeOutputModel.getIdentificationNumber()).isEqualTo(employeeEntity.getIdentificationNumber()),
        () -> assertThat(employeeOutputModel.getName()).isEqualTo(employeeEntity.getName()),
        () -> assertThat(employeeOutputModel.getCompanyRole()).isEqualTo(employeeEntity.getCompanyRole())
    );
  }

  @Test
  public void addEmployeeTest_throwsResourceNotFoundException() {
    EmployeeInputModel employeeInputModel = EmployeeUtils.generateEmployeeInputModel();
    when(locationRepositoryMock.existsById(employeeInputModel.getLocationId())).thenReturn(false);
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class,
            () -> employeeService.addEmployee(employeeInputModel));
    assertThat(exception.getMessage()).isEqualTo(ExceptionConstants.LOCATION_NOT_FOUND);
    verify(locationRepositoryMock, times(1))
        .existsById(employeeInputModel.getLocationId());
  }

  @Test
  public void addEmployeeTest_throwsActionNotAllowedException() {
    EmployeeInputModel employeeInputModel = EmployeeUtils.generateEmployeeInputModel();
    when(locationRepositoryMock.existsById(employeeInputModel.getLocationId())).thenReturn(true);
    when(employeeRepositoryMock.existsByIdentificationNumber(employeeInputModel.getIdentificationNumber()))
        .thenReturn(true);
    ActionNotAllowedException exception =
        assertThrows(ActionNotAllowedException.class,
            () -> employeeService.addEmployee(employeeInputModel));
    assertThat(exception.getMessage()).isEqualTo(ExceptionConstants.EMPLOYEE_ALREADY_EXISTS);
    verify(locationRepositoryMock, times(1))
        .existsById(employeeInputModel.getLocationId());
    verify(employeeRepositoryMock, times(1))
        .existsByIdentificationNumber(employeeInputModel.getIdentificationNumber());
  }

  @Test
  public void removeEmployeeTest() {
    when(employeeRepositoryMock.existsById(employeeId))
        .thenReturn(true);
    doNothing().when(employeeRepositoryMock).deleteById(employeeId);
    Boolean shouldBeTrue = employeeService.removeEmployee(employeeId);
    assertThat(shouldBeTrue).isTrue();
    verify(employeeRepositoryMock, times(1)).existsById(employeeId);
    verify(employeeRepositoryMock, times(1)).deleteById(employeeId);
  }

  @Test
  public void removeEmployeeTest_throwsResourceNotFoundException() {
    when(employeeRepositoryMock.existsById(employeeId))
        .thenReturn(false);
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class,
            () -> employeeService.removeEmployee(employeeId));
    assertThat(exception.getMessage()).isEqualTo(ExceptionConstants.EMPLOYEE_NOT_FOUND);
    verify(employeeRepositoryMock, times(1))
        .existsById(employeeId);
  }

  @Test
  public void getAllEmployeesTest_locationIdNotNull() {
    Long locationId = 6L;
    when(employeeRepositoryMock.findAllByLocationId(locationId))
        .thenReturn(employees);
    List<EmployeeOutputModel> employeeOutputModels = employeeService.getAllEmployees(locationId);
    assertThat(employeeOutputModels).hasSize(employees.size());
    for(int i = 0; i < employees.size(); ++i) {
      EmployeeEntity employeeEntity = employees.get(i);
      EmployeeOutputModel employeeOutputModel = employeeOutputModels.get(i);
      assertAll(
          () -> assertThat(employeeOutputModel.getId()).isEqualTo(employeeEntity.getId()),
          () -> assertThat(employeeOutputModel.getIdentificationNumber()).isEqualTo(employeeEntity.getIdentificationNumber()),
          () -> assertThat(employeeOutputModel.getName()).isEqualTo(employeeEntity.getName()),
          () -> assertThat(employeeOutputModel.getCompanyRole()).isEqualTo(employeeEntity.getCompanyRole())
      );
    }
  }

  @Test
  public void getAllEmployeesTest_locationIdNull() {
    Long locationId = null;
    when(employeeRepositoryMock.findAll())
        .thenReturn(employees);
    List<EmployeeOutputModel> employeeOutputModels = employeeService.getAllEmployees(locationId);
    assertThat(employeeOutputModels).hasSize(employees.size());
    for(int i = 0; i < employees.size(); ++i) {
      EmployeeEntity employeeEntity = employees.get(i);
      EmployeeOutputModel employeeOutputModel = employeeOutputModels.get(i);
      assertAll(
          () -> assertThat(employeeOutputModel.getId()).isEqualTo(employeeEntity.getId()),
          () -> assertThat(employeeOutputModel.getIdentificationNumber()).isEqualTo(employeeEntity.getIdentificationNumber()),
          () -> assertThat(employeeOutputModel.getName()).isEqualTo(employeeEntity.getName()),
          () -> assertThat(employeeOutputModel.getCompanyRole()).isEqualTo(employeeEntity.getCompanyRole())
      );
    }
  }
}
