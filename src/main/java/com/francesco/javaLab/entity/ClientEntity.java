package com.francesco.javaLab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "identification_number")
  private String identificationNumber;

  @OneToMany(mappedBy = "client")
  private List<RentalEntity> rentals;

}
