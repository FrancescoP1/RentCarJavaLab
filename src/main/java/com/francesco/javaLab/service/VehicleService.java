package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.LocationEntity;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotAvailableException;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.mapper.VehicleEntityMapper;
import com.francesco.javaLab.model.input.VehicleInputModel;
import com.francesco.javaLab.model.output.VehicleOutputModel;
import com.francesco.javaLab.repository.LocationRepository;
import com.francesco.javaLab.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  private final LocationRepository locationRepository;

  private final VehicleEntityMapper vehicleEntityMapper =
      VehicleEntityMapper.INSTANCE;

  public VehicleOutputModel addVehicle(VehicleInputModel vehicleInputModel) {
    VehicleEntity vehicle = vehicleEntityMapper.fromInputModelToEntity(vehicleInputModel);
    vehicle = vehicleRepository.save(vehicle);
    return vehicleEntityMapper.fromEntityToOutputModel(vehicle);
  }

  public VehicleOutputModel getVehicleById(Long vehicleId) {
    VehicleEntity vehicleEntity = this.findVehicleById(vehicleId);
    return vehicleEntityMapper.fromEntityToOutputModel(vehicleEntity);
  }

  public Boolean deleteVehicleById(Long vehicleId) {
    VehicleEntity vehicleEntity = this.findVehicleById(vehicleId);
    vehicleRepository.delete(vehicleEntity);
    return true;
  }

  public VehicleOutputModel updateVehicleById(
      Long vehicleId, VehicleInputModel vehicleInputModel) {
    VehicleEntity vehicleEntity = this.findVehicleById(vehicleId);
    updateVehicleEntityFromModel(vehicleEntity, vehicleInputModel);
    vehicleRepository.save(vehicleEntity);
    return vehicleEntityMapper.fromEntityToOutputModel(vehicleEntity);
  }

  public Boolean rentVehicle(VehicleEntity vehicleEntity) {
    if (!vehicleEntity.getIsRented()) {
      vehicleEntity.setIsRented(true);
      vehicleRepository.save(vehicleEntity);
      return true;
    }
    throw new ResourceNotAvailableException(ExceptionConstants.VEHICLE_NOT_AVAILABLE);
  }

  public Boolean unRentVehicle(VehicleEntity vehicleEntity) {
    vehicleEntity.setIsRented(false);
    vehicleRepository.save(vehicleEntity);
    return true;
  }

  public VehicleEntity findVehicleById(Long vehicleId) {
    Optional<VehicleEntity> vehicleEntityOptional = vehicleRepository.findById(vehicleId);
    if(vehicleEntityOptional.isEmpty()) {
      throw new ResourceNotFoundException(ExceptionConstants.VEHICLE_NOT_FOUND);
    }
    return vehicleEntityOptional.get();
  }

  private void updateVehicleEntityFromModel(
      VehicleEntity vehicleEntity, VehicleInputModel vehicleInputModel) {
    Optional<LocationEntity> locationEntityOptional =
        locationRepository.findById(vehicleInputModel.getLocationId());
    if(locationEntityOptional.isEmpty()) {
      throw new RuntimeException();
    }
    vehicleEntity.setLocation(locationEntityOptional.get());
    vehicleEntity.setModel(vehicleInputModel.getModel());
    vehicleEntity.setBrand(vehicleInputModel.getBrand());
    vehicleEntity.setModelYear(vehicleInputModel.getModelYear());
    vehicleEntity.setOdometerReading(vehicleInputModel.getOdometerReading());
    vehicleEntity.setVin(vehicleInputModel.getVin());
  }
}
