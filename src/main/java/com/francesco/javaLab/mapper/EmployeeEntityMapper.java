package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.EmployeeEntity;
import com.francesco.javaLab.model.input.EmployeeInputModel;
import com.francesco.javaLab.model.output.EmployeeOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {LocationEntityMapper.class}
)
public interface EmployeeEntityMapper {

  EmployeeEntityMapper INSTANCE = Mappers.getMapper(EmployeeEntityMapper.class);

  @Mapping(source = "locationId", target = "location.id")
  EmployeeEntity fromInputModelToEntity(EmployeeInputModel employeeInputModel);

  EmployeeOutputModel fromEntityToOutputModel(EmployeeEntity employeeEntity);

  List<EmployeeOutputModel> fromEntitiesToOutputModels(List<EmployeeEntity> employeeEntityList);
}
