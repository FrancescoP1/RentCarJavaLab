package com.francesco.javaLab.mapper;

import com.francesco.javaLab.entity.ReviewEntity;
import com.francesco.javaLab.model.input.ReviewInputModel;
import com.francesco.javaLab.model.output.ReviewOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {RentalEntityMapper.class}
)
public interface ReviewEntityMapper {

  ReviewEntityMapper INSTANCE = Mappers.getMapper(ReviewEntityMapper.class);

  @Mapping(source = "rentalId", target = "rental.id")
  ReviewEntity fromInputToEntity(ReviewInputModel reviewInputModel);

  ReviewOutputModel fromEntityToOutputModel(ReviewEntity reviewEntity);
}
