package com.francesco.javaLab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date startDate;

  @Column(name = "end_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date endDate;

  @Column(name = "is_finished")
  private Boolean isFinished = false;

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  private VehicleEntity vehicle;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private ClientEntity client;

  @OneToOne(mappedBy = "rental")
  private ReviewEntity review;
}
