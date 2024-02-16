package com.francesco.javaLab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.francesco.javaLab.entity.VehicleEntity;
import com.francesco.javaLab.model.input.VehicleInputModel;
import com.francesco.javaLab.model.output.VehicleOutputModel;
import com.francesco.javaLab.service.VehicleService;
import com.francesco.javaLab.testUtils.VehicleUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean
  private VehicleService vehicleService;

  @Test
  public void testAddVehicle() throws Exception {
    VehicleEntity vehicleEntity = VehicleUtils.generateVehicleEntity();
    VehicleInputModel inputModel = VehicleUtils.generateVehicleInputModel(vehicleEntity);
    VehicleOutputModel outputModel = VehicleUtils.generateVehicleOutputModel(vehicleEntity);
    when(vehicleService.addVehicle(any())).thenReturn(outputModel);
    mockMvc.perform(MockMvcRequestBuilders.post("/vehicle")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(inputModel)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(vehicleEntity.getId())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicleEntity.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicleEntity.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.modelYear").value(vehicleEntity.getModelYear()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.vin").value(vehicleEntity.getVin()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.odometerReading").value(vehicleEntity.getOdometerReading()));

  }

  @Test
  public void testGetVehicleById() throws Exception {
    VehicleEntity vehicleEntity = VehicleUtils.generateVehicleEntity();
    Long vehicleId = vehicleEntity.getId();
    VehicleOutputModel outputModel = VehicleUtils.generateVehicleOutputModel(vehicleEntity);

    when(vehicleService.getVehicleById(vehicleId)).thenReturn(outputModel);

    mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/{vehicle_id}", vehicleId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(vehicleEntity.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicleEntity.getBrand()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicleEntity.getModel()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.modelYear").value(vehicleEntity.getModelYear()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.vin").value(vehicleEntity.getVin()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.odometerReading").value(vehicleEntity.getOdometerReading()));
  }

  @Test
  public void testDeleteVehicleById() throws Exception {
    Long vehicleId = 1L;

    when(vehicleService.deleteVehicleById(vehicleId)).thenReturn(true);

    mockMvc.perform(MockMvcRequestBuilders.delete("/vehicle/{vehicle_id}", vehicleId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
  }

  @Test
  public void testEditVehicleById() throws Exception {
    /*
    Long vehicleId = 1L;
    VehicleInputModel inputModel = new VehicleInputModel( Set input model fields );
    VehicleOutputModel outputModel = new VehicleOutputModel(/* Set output model fields );

    when(vehicleService.updateVehicleById(vehicleId, inputModel)).thenReturn(outputModel);

    mockMvc.perform(MockMvcRequestBuilders.put("/vehicle/{vehicle_id}", vehicleId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(inputModel)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.field1").value(/* expected value ))
        .andExpect(MockMvcResultMatchers.jsonPath("$.field2").value(/* expected value ));
    */
  }
}

