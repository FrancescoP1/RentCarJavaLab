package com.francesco.javaLab.controller;

import com.francesco.javaLab.model.input.EmployeeInputModel;
import com.francesco.javaLab.model.output.EmployeeOutputModel;
import com.francesco.javaLab.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping("")
  public ResponseEntity<EmployeeOutputModel> addEmployee(
      @Valid @RequestBody EmployeeInputModel employeeInputModel) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(employeeService.addEmployee(employeeInputModel));
  }

  @DeleteMapping("/{employeeId}")
  public ResponseEntity<Boolean> removeEmployee(
      @PathVariable(name = "employeeId") Long employeeId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(employeeService.removeEmployee(employeeId));
  }

  @GetMapping("")
  public ResponseEntity<List<EmployeeOutputModel>> getEmployees(
      @RequestParam(name = "locationId") Long locationId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(employeeService.getAllEmployees(locationId));
  }
}
