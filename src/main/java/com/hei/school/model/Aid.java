package com.hei.school.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aids")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private Beneficiary beneficiary;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private String accidentDescription;
}
