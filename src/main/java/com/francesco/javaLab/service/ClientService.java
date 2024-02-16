package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRepository;

  public ClientEntity findClientById(Long clientId) {
    Optional<ClientEntity> clientEntityOptional =
        clientRepository.findById(clientId);
    if(clientEntityOptional.isEmpty()) {
      throw new ResourceNotFoundException(ExceptionConstants.CLIENT_NOT_FOUND);
    }
    return clientEntityOptional.get();
  }
}
