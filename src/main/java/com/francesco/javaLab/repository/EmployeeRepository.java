package com.francesco.javaLab.repository;

import com.francesco.javaLab.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
  List<EmployeeEntity> findAllByLocationId(Long locationId);
  Boolean existsByIdentificationNumber(String identificationNumber);
}
