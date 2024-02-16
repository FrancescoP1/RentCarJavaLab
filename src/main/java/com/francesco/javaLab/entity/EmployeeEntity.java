package com.francesco.javaLab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "identification_number")
  private String identificationNumber;

  @Column(name = "company_role")
  private String companyRole;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private LocationEntity location;
}
