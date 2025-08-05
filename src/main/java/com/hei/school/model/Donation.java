package com.hei.school.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;
}
