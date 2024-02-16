package com.francesco.javaLab.controller;

import com.francesco.javaLab.model.input.VehicleInputModel;
import com.francesco.javaLab.model.output.VehicleOutputModel;
import com.francesco.javaLab.service.VehicleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;

  @PostMapping("")
  public ResponseEntity<VehicleOutputModel> addVehicle(
      @Valid @RequestBody VehicleInputModel vehicleInputModel) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(vehicleService.addVehicle(vehicleInputModel));
  }

  @GetMapping("/{vehicle_id}")
  public ResponseEntity<VehicleOutputModel> getVehicleById(
      @NotNull @PathVariable(name = "vehicle_id") Long vehicleId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(vehicleService.getVehicleById(vehicleId));
  }

  @DeleteMapping("/{vehicle_id}")
  public ResponseEntity<Boolean> deleteVehicleById(
      @NotNull @PathVariable(name = "vehicle_id") Long vehicleId
  ) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(vehicleService.deleteVehicleById(vehicleId));
  }

  @PutMapping("/{vehicle_id}")
  public ResponseEntity<VehicleOutputModel> editVehicleById(
      @NotNull @PathVariable(name = "vehicle_id") Long vehicleId,
      @Valid @RequestBody VehicleInputModel vehicleInputModel) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(vehicleService.updateVehicleById(vehicleId, vehicleInputModel));
  }
}
