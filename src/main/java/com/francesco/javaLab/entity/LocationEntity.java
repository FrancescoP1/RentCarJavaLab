package com.francesco.javaLab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "location_name")
  private String locationName;

  @Column(name = "city")
  private String city;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private List<VehicleEntity> vehicles;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private List<EmployeeEntity> employees;

}
