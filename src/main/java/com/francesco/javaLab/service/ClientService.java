package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.exception.ActionNotAllowedException;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.mapper.ClientEntityMapper;
import com.francesco.javaLab.model.input.ClientInputModel;
import com.francesco.javaLab.model.output.ClientOutputModel;
import com.francesco.javaLab.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRepository;
  private final ClientEntityMapper clientEntityMapper = ClientEntityMapper.INSTANCE;

  @Transactional
  public ClientOutputModel createNewClient(ClientInputModel clientInputModel) {
    ClientEntity clientEntity =
        clientEntityMapper.fromInputModelToEntity(clientInputModel);
    if(clientRepository.existsByIdentificationNumber(clientEntity.getIdentificationNumber())) {
      throw new ActionNotAllowedException(ExceptionConstants.CLIENT_ALREADY_EXISTS);
    }
    clientEntity = clientRepository.save(clientEntity);
    return clientEntityMapper.fromEntityToOutputModel(clientEntity);
  }

  @Transactional
  public void deleteClientById(Long clientId) {
    ClientEntity clientEntity = this.findClientById(clientId);
    clientRepository.delete(clientEntity);
  }

  public List<ClientOutputModel> getAllClients() {
    List<ClientEntity> clientEntities = clientRepository.findAll();
    return clientEntityMapper.fromEntityListToOutputList(clientEntities);
  }

  public ClientEntity findClientById(Long clientId) {
    Optional<ClientEntity> clientEntityOptional =
        clientRepository.findById(clientId);
    if(clientEntityOptional.isEmpty()) {
      throw new ResourceNotFoundException(ExceptionConstants.CLIENT_NOT_FOUND);
    }
    return clientEntityOptional.get();
  }
}
