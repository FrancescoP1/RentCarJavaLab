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
@Table(name = "vehicles")
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "brand")
  private String brand;

  @Column(name = "model")
  private String model;

  @Column(name = "model_year")
  private Integer modelYear;

  @Column(name = "vin", unique = true)
  private String vin;

  @Column(name = "ODOMETER_READING")
  private Integer odometerReading;

  @Column(name = "is_rented")
  private Boolean isRented = false;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private LocationEntity location;

  @OneToMany(mappedBy = "vehicle")
  private List<RentalEntity> rentals;
}
