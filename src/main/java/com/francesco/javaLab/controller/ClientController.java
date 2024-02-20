package com.francesco.javaLab.controller;

import com.francesco.javaLab.model.input.ClientInputModel;
import com.francesco.javaLab.model.output.ClientOutputModel;
import com.francesco.javaLab.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @PostMapping("")
  public ResponseEntity<ClientOutputModel> createNewClient(
      @Valid @RequestBody ClientInputModel clientInputModel) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(clientService.createNewClient(clientInputModel));
  }

  @DeleteMapping("/{clientId}")
  public ResponseEntity<Void> deleteClient(
      @PathVariable(name = "clientId") Long clientId) {
    clientService.deleteClientById(clientId);
    return ResponseEntity
        .status(HttpStatus.OK)
        .build();
  }

  @GetMapping("")
  public ResponseEntity<List<ClientOutputModel>> getAllClients() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(clientService.getAllClients());
  }
}
