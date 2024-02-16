package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.entity.RentalEntity;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.mapper.RentalEntityMapper;
import com.francesco.javaLab.model.input.RentalInputModel;
import com.francesco.javaLab.model.output.RentalOutputModel;
import com.francesco.javaLab.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    VehicleEntity vehicleEntity = vehicleService.findVehicleById(rentalInputModel.getVehicleId());
    ClientEntity clientEntity = clientService.findClientById(rentalInputModel.getClientId());
    vehicleService.rentVehicle(vehicleEntity);
    rentalEntity.setVehicle(vehicleEntity);
    rentalEntity.setClient(clientEntity);
    rentalRepository.save(rentalEntity);
    return rentalEntityMapper.fromEntityToOutputModel(rentalEntity);
  }
}
