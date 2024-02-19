package com.francesco.javaLab.repository;

import com.francesco.javaLab.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
  Boolean existsByVehicleIdAndEndDateAfter(Long vehicleId, Date endDate);
}
