package com.francesco.javaLab.service;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.entity.RentalEntity;
import com.francesco.javaLab.model.input.RentalInputModel;
import com.francesco.javaLab.model.output.RentalOutputModel;
import com.francesco.javaLab.repository.RentalRepository;
import com.francesco.javaLab.testUtils.ClientUtils;
import com.francesco.javaLab.testUtils.RentalUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

  @Mock
  private RentalRepository rentalRepositoryMock;
  @Mock
  private VehicleService vehicleServiceMock;
  @Mock
  private ClientService clientServiceMock;

  @InjectMocks @Spy
  private RentalService rentalService;

  private Long rentalId;

  @BeforeEach
  public void setup() {
    rentalId = 10L;
  }

  @Test
  public void addRentalTest() {
    RentalInputModel rentalInputModel = RentalUtils.generateRentalInput();
    RentalEntity rentalEntity = RentalUtils.generateRental(rentalId, rentalInputModel);
    ClientEntity clientEntity = ClientUtils.generateClientEntity(rentalInputModel.getClientId(), null);
    doReturn(rentalEntity.getVehicle()).when(rentalService)
        .checkVehicleAvailability(rentalInputModel.getVehicleId());
    when(clientServiceMock.findClientById(rentalInputModel.getClientId()))
        .thenReturn(clientEntity);
    when(vehicleServiceMock.rentVehicle(any())).thenReturn(true);
    when(rentalRepositoryMock.save(any())).thenReturn(rentalEntity);
    RentalOutputModel rentalOutputModel = rentalService.addRental(rentalInputModel);
    verify(rentalService, times(1))
        .checkVehicleAvailability(rentalInputModel.getVehicleId());
    verify(clientServiceMock, times(1))
        .findClientById(rentalInputModel.getClientId());
    verify(rentalRepositoryMock, times(1)).save(any());
    assertThat(rentalOutputModel.getId()).isEqualTo(rentalEntity.getId());
  }
}
