package com.hei.school.service;

import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;

    public List<Donation> getAllDonationsSorted() {
        return donationRepository.findAllOrderByPaymentDateDesc();
    }

    public Donation saveDonation(Donation donation) {
        // Vérifier si le Donor existe déjà par email
        String email = donation.getDonor().getEmail();
        Donor donor = donorRepository.findByEmail(email).orElse(null);

        if (donor == null) {
            // Créer un nouveau Donor
            donor = donation.getDonor();
            donor = donorRepository.save(donor);
        }

        donation.setDonor(donor);

        return donationRepository.save(donation);
    }

    public Double getTotalDonatedAmount() {
        return donationRepository.getTotalDonatedAmount();
    }
}
