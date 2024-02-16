package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.RentalEntity;
import com.francesco.javaLab.model.input.RentalInputModel;
import com.francesco.javaLab.model.output.RentalOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {VehicleEntityMapper.class}
)
public interface RentalEntityMapper {

  RentalEntityMapper INSTANCE = Mappers.getMapper(RentalEntityMapper.class);

  @Mapping(source = "vehicleId", target = "vehicle.id")
  @Mapping(source = "clientId", target = "client.id")
  RentalEntity fromInputModelToEntity(RentalInputModel rentalInputModel);

  RentalOutputModel fromEntityToOutputModel(RentalEntity rentalEntity);
}
