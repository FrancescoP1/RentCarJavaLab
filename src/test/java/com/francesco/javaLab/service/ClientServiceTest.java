package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.exception.ActionNotAllowedException;
import com.francesco.javaLab.exception.ExceptionConstants;
import com.francesco.javaLab.exception.ResourceNotFoundException;
import com.francesco.javaLab.model.input.ClientInputModel;
import com.francesco.javaLab.model.output.ClientOutputModel;
import com.francesco.javaLab.repository.ClientRepository;
import com.francesco.javaLab.testUtils.ClientUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
  @Mock
  private ClientRepository clientRepositoryMock;
  @InjectMocks @Spy
  private ClientService clientService;

  private Long clientId;

  @BeforeEach
  public void setup() {
    clientId  = 5L;
  }

  @Test
  public void createNewClientTest_throwsActionNotAllowedException() {
    ClientInputModel clientInputModel = ClientUtils.generateClientInputModel();
    when(clientRepositoryMock
        .existsByIdentificationNumber(clientInputModel.getIdentificationNumber()))
        .thenReturn(true);
    ActionNotAllowedException exception =
        assertThrows(ActionNotAllowedException.class,
            () -> clientService.createNewClient(clientInputModel));
    assertThat(exception.getMessage()).isEqualTo(ExceptionConstants.CLIENT_ALREADY_EXISTS);
    verify(clientRepositoryMock, times(1))
        .existsByIdentificationNumber(clientInputModel.getIdentificationNumber());
  }

  @Test
  public void createNewClientTest() {
    ClientInputModel clientInputModel = ClientUtils.generateClientInputModel();
    ClientEntity clientEntity = ClientUtils.generateClientEntity(clientId, clientInputModel);
    when(clientRepositoryMock
        .existsByIdentificationNumber(clientInputModel.getIdentificationNumber()))
        .thenReturn(false);
    when(clientRepositoryMock.save(any())).thenReturn(clientEntity);
    ClientOutputModel clientOutputModel =
        assertDoesNotThrow(() -> clientService.createNewClient(clientInputModel));
    verify(clientRepositoryMock, times(1))
        .existsByIdentificationNumber(clientInputModel.getIdentificationNumber());
    verify(clientRepositoryMock, times(1))
        .save(any());
    assertAll(
        () -> assertThat(clientOutputModel.getId()).isEqualTo(clientEntity.getId()),
        () -> assertThat(clientOutputModel.getIdentificationNumber()).isEqualTo(clientEntity.getIdentificationNumber()),
        () -> assertThat(clientOutputModel.getName()).isEqualTo(clientEntity.getName())
    );
  }

  @Test
  public void deleteClientByIdTest() {
    ClientEntity clientEntity = ClientUtils.generateClientEntity(clientId, null);
    doReturn(clientEntity).when(clientService).findClientById(clientId);
    clientService.deleteClientById(clientId);
    verify(clientRepositoryMock, times(1)).delete(any());
  }

  @Test
  public void getAllClientsTest() {
    List<ClientEntity> clientEntities =
        new ArrayList<>(Arrays.asList(
            ClientUtils.generateClientEntity(clientId, null),
            ClientUtils.generateClientEntity(clientId, null)));
    when(clientRepositoryMock.findAll()).thenReturn(clientEntities);
    List<ClientOutputModel> clientOutputModels = clientService.getAllClients();
    assertThat(clientOutputModels).hasSize(clientEntities.size());
    for(int i = 0; i < clientOutputModels.size(); ++i) {
      ClientEntity clientEntity = clientEntities.get(i);
      ClientOutputModel clientOutputModel = clientOutputModels.get(i);
      assertAll(
          () -> assertThat(clientOutputModel.getId()).isEqualTo(clientEntity.getId()),
          () -> assertThat(clientOutputModel.getIdentificationNumber()).isEqualTo(clientEntity.getIdentificationNumber()),
          () -> assertThat(clientOutputModel.getName()).isEqualTo(clientEntity.getName())
      );
    }
  }

  @Test
  public void findClientByIdTest_throwsResourceNotFoundException() {
    when(clientRepositoryMock.findById(clientId)).thenReturn(Optional.empty());
    ResourceNotFoundException exception =
        assertThrows(
            ResourceNotFoundException.class,
            () -> clientService.findClientById(clientId));
    assertThat(exception.getMessage()).isEqualTo(ExceptionConstants.CLIENT_NOT_FOUND);
    verify(clientRepositoryMock, times(1)).findById(clientId);
  }

  @Test
  public void findClientByIdTest() {
    ClientEntity clientEntity = ClientUtils.generateClientEntity(clientId, null);
    when(clientRepositoryMock.findById(clientId)).thenReturn(Optional.of(clientEntity));
    assertDoesNotThrow(() -> clientService.findClientById(clientId));
    verify(clientRepositoryMock, times(1)).findById(clientId);
  }
}
