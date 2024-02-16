package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.LocationEntity;
import com.francesco.javaLab.model.input.LocationInputModel;
import com.francesco.javaLab.model.output.LocationOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationEntityMapper {

  LocationEntityMapper INSTANCE = Mappers.getMapper(LocationEntityMapper.class);

  LocationOutputModel fromEntityToOutputModel(LocationEntity locationEntity);

  LocationEntity fromInputModelToEntity(LocationInputModel locationInputModel);

  LocationInputModel fromEntityToInputModel(LocationEntity locationEntity);
}
