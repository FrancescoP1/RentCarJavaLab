package com.francesco.javaLab.testUtils;

import com.francesco.javaLab.entity.ClientEntity;
import com.francesco.javaLab.model.input.ClientInputModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

public class ClientUtils {
  public static ClientEntity generateClientEntity(Long clientId, ClientInputModel clientInputModel) {
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setId(clientId);
    if(clientInputModel == null) {
      clientEntity.setName(RandomStringUtils.randomAlphabetic(20));
      clientEntity.setIdentificationNumber(RandomStringUtils.randomAlphanumeric(15));
    } else {
      clientEntity.setName(clientInputModel.getName());
      clientEntity.setIdentificationNumber(clientInputModel.getIdentificationNumber());
    }
    clientEntity.setRentals(new ArrayList<>());
    return clientEntity;
  }

  public static ClientInputModel generateClientInputModel() {
    ClientInputModel clientInputModel = new ClientInputModel();
    clientInputModel.setName(RandomStringUtils.randomAlphabetic(20));
    clientInputModel.setIdentificationNumber(RandomStringUtils.randomAlphanumeric(15));
    return clientInputModel;
  }
}
