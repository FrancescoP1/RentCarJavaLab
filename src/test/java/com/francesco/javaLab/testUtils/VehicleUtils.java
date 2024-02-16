package com.francesco.javaLab.testUtils;


import com.francesco.javaLab.entity.LocationEntity;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.mapper.VehicleEntityMapper;
import com.francesco.javaLab.model.input.VehicleInputModel;
import com.francesco.javaLab.model.output.VehicleOutputModel;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.Random;

public class VehicleUtils {

  private final static VehicleEntityMapper vehicleEntityMapper = VehicleEntityMapper.INSTANCE;

  public static VehicleEntity generateVehicleEntity() {
    VehicleEntity vehicle = new VehicleEntity();
    Random random = new Random();
    vehicle.setId(random.nextLong(0, Long.MAX_VALUE));
    vehicle.setBrand(RandomStringUtils.randomAlphanumeric(5));
    vehicle.setModel(RandomStringUtils.randomAlphanumeric(10));
    vehicle.setModelYear(random.nextInt(1900, 2024));
    vehicle.setOdometerReading(random.nextInt());
    vehicle.setIsRented(random.nextBoolean());
    vehicle.setVin(RandomStringUtils.random(17));
    vehicle.setLocation(LocationUtils.generateLocationEntity());
    return vehicle;
  }

  public static VehicleOutputModel generateVehicleOutputModel(VehicleEntity vehicleEntity) {
    return vehicleEntityMapper.fromEntityToOutputModel(vehicleEntity);
  }

  public static VehicleInputModel generateVehicleInputModel(VehicleEntity vehicleEntity) {
    return vehicleEntityMapper.fromEntityToInputModel(vehicleEntity);
  }
}
