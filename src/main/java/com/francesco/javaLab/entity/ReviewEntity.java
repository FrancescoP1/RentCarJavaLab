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
public class ReviewEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "comment")
  private String comment;

  @Column(name = "rating")
  private Integer rating;

  @OneToOne
  @JoinColumn(name = "rental_id")
  private RentalEntity rental;
}
