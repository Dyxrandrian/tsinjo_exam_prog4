package com.hei.school.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  private String paymentMethod;

  @Column(nullable = false)
  private LocalDate paymentDate;

  @Column(nullable = false)
  private String paymentReference;

  @Column(nullable = false)
  private String pspType;

  @Column(nullable = false)
  private String pspPaymentId;
}
