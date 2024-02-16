package com.francesco.javaLab.testUtils;

import com.francesco.javaLab.entity.LocationEntity;
import com.francesco.javaLab.mapper.LocationEntityMapper;
import com.francesco.javaLab.model.input.LocationInputModel;
import com.francesco.javaLab.model.output.LocationOutputModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class LocationUtils {

  private final static LocationEntityMapper locationEntityMapper = LocationEntityMapper.INSTANCE;

  public static LocationEntity generateLocationEntity() {
    LocationEntity locationEntity = new LocationEntity();
    Random random = new Random();
    locationEntity.setId(random.nextLong());
    locationEntity.setLocationName(RandomStringUtils.randomAlphabetic(10));
    locationEntity.setCity(RandomStringUtils.random(10));
    return locationEntity;
  }

  public static LocationOutputModel generateLocationOutputModel() {
    return locationEntityMapper.fromEntityToOutputModel(generateLocationEntity());
  }

  public static LocationInputModel generateLocationInputModel() {
    return locationEntityMapper.fromEntityToInputModel(generateLocationEntity());
  }
}
