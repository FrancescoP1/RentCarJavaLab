package com.francesco.javaLab.controller;

import com.francesco.javaLab.model.input.RentalInputModel;
import com.francesco.javaLab.model.output.RentalOutputModel;
import com.francesco.javaLab.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rent")
@RequiredArgsConstructor
public class RentalController {

  private final RentalService rentalService;

  @PostMapping("")
  public ResponseEntity<RentalOutputModel> rentVehicle(
      @Valid @RequestBody RentalInputModel rentalInputModel) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(rentalService.addRental(rentalInputModel));
  }

  @DeleteMapping("/{rentalId}")
  public ResponseEntity<Void> deleteRental(
      @PathVariable(name = "rentalId") Long rentalId) {
    rentalService.deleteRental(rentalId);
    return ResponseEntity
        .status(HttpStatus.OK).build();
  }

  @PutMapping("/{rentalId}")
  public ResponseEntity<RentalOutputModel>  finishRental(
      @PathVariable(name = "rentalId") Long rentalId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(rentalService.finishRental(rentalId));
  }
}
