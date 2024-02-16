package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.model.input.VehicleInputModel;
import com.francesco.javaLab.model.output.VehicleOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface VehicleEntityMapper {

  VehicleEntityMapper INSTANCE = Mappers.getMapper(VehicleEntityMapper.class);

  VehicleOutputModel fromEntityToOutputModel(VehicleEntity vehicleEntity);

  @Mapping(source = "locationId", target = "location.id")
  VehicleEntity fromInputModelToEntity(VehicleInputModel vehicleInputModel);

  @Mapping(source = "location.id", target = "locationId")
  VehicleInputModel fromEntityToInputModel(VehicleEntity vehicleEntity);
}
