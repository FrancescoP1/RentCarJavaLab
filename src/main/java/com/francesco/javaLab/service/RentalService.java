package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.entity.RentalEntity;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.exception.ActionNotAllowedException;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotAvailableException;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.mapper.RentalEntityMapper;
import com.francesco.javaLab.model.input.RentalInputModel;
import com.francesco.javaLab.model.output.RentalOutputModel;
import com.francesco.javaLab.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;
  private final VehicleService vehicleService;
  private final ClientService clientService;

  private final RentalEntityMapper rentalEntityMapper = RentalEntityMapper.INSTANCE;

  @Transactional
  public RentalOutputModel addRental(RentalInputModel rentalInputModel) {
    RentalEntity rentalEntity = rentalEntityMapper.fromInputModelToEntity(rentalInputModel);
    VehicleEntity vehicleEntity = checkVehicleAvailability(rentalInputModel.getVehicleId());
    ClientEntity clientEntity = clientService.findClientById(rentalInputModel.getClientId());
    vehicleService.rentVehicle(vehicleEntity);
    rentalEntity.setVehicle(vehicleEntity);
    rentalEntity.setClient(clientEntity);
    rentalEntity = rentalRepository.save(rentalEntity);
    return rentalEntityMapper.fromEntityToOutputModel(rentalEntity);
  }

  @Transactional
  public void deleteRental(Long rentalId) {
    RentalEntity rentalEntity = getRentalById(rentalId);
    rentalRepository.delete(rentalEntity);
  }

  public RentalOutputModel finishRental(Long rentalId) {
    RentalEntity rentalEntity = getRentalById(rentalId);
    if(rentalEntity.getIsFinished()) {
      throw new ActionNotAllowedException(ExceptionConstants.RENTAL_ALREADY_FINISHED);
    }
    rentalEntity.setIsFinished(true);
    rentalEntity.setEndDate(new Date());
    rentalEntity = rentalRepository.save(rentalEntity);
    return rentalEntityMapper.fromEntityToOutputModel(rentalEntity);
  }

  public VehicleEntity checkVehicleAvailability(Long vehicleId) {
    VehicleEntity vehicleEntity = vehicleService.findVehicleById(vehicleId);
    if(vehicleEntity.getIsRented()) {
      if(rentalRepository
          .existsByVehicleIdAndEndDateAfter(vehicleEntity.getId(), new Date())) {
        throw new ResourceNotAvailableException(ExceptionConstants.VEHICLE_ALREADY_RENTED);
      }
      vehicleService.unRentVehicle(vehicleEntity);
    }
    return vehicleEntity;
  }

  public RentalEntity getRentalById(Long rentalId) {
    Optional<RentalEntity> rentalEntityOptional = rentalRepository.findById(rentalId);
    if(rentalEntityOptional.isEmpty()) {
      throw new ResourceNotFoundException(ExceptionConstants.RENTAL_NOT_FOUND);
    }
    return rentalEntityOptional.get();
  }
}
