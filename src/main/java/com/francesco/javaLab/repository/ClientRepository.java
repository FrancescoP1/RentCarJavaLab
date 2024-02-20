package com.francesco.javaLab.repository;

import com.francesco.javaLab.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
  Boolean existsByIdentificationNumber(String identificationNumber);
}
