package com.francesco.javaLab.testUtils;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.entity.RentalEntity;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.model.input.RentalInputModel;

import java.util.Date;
import java.util.Random;

public class RentalUtils {

  public static RentalEntity generateRental(Long RentalId, RentalInputModel rentalInputModel) {
    RentalEntity rentalEntity = new RentalEntity();
    rentalEntity.setId(RentalId);
    rentalEntity.setIsFinished(false);
    if(rentalInputModel == null) {
      rentalEntity.setVehicle(VehicleUtils.generateVehicleEntity());
      rentalEntity.setStartDate(new Date());
      rentalEntity.setEndDate(new Date());
      rentalEntity.setClient(ClientUtils.generateClientEntity(30L, null));
    } else {
      VehicleEntity vehicleEntity = new VehicleEntity();
      vehicleEntity.setId(rentalInputModel.getVehicleId());
      ClientEntity clientEntity = new ClientEntity();
      clientEntity.setId(rentalInputModel.getClientId());
      rentalEntity.setVehicle(vehicleEntity);
      rentalEntity.setClient(clientEntity);
      rentalEntity.setStartDate(rentalInputModel.getStartDate());
      rentalEntity.setEndDate(rentalInputModel.getEndDate());
    }
    return rentalEntity;
  }

  public static RentalInputModel generateRentalInput() {
    Random random = new Random();
    RentalInputModel rentalInputModel = new RentalInputModel();
    rentalInputModel.setStartDate(new Date());
    rentalInputModel.setEndDate(new Date());
    rentalInputModel.setClientId(random.nextLong(1, 1000));
    rentalInputModel.setVehicleId(random.nextLong(1, 1000));
    return rentalInputModel;
  }
}
