package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.model.input.ClientInputModel;
import com.francesco.javaLab.model.output.ClientOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientEntityMapper {
  ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);

  ClientEntity fromInputModelToEntity(ClientInputModel clientInputModel);

  ClientOutputModel fromEntityToOutputModel(ClientEntity clientEntity);

  List<ClientOutputModel> fromEntityListToOutputList(List<ClientEntity> clientEntities);

}
