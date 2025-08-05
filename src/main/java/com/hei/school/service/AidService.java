package com.hei.school.service;

import com.hei.school.model.Aid;
import com.hei.school.model.Beneficiary;
import com.hei.school.repository.AidRepository;
import com.hei.school.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AidService {

    private final AidRepository aidRepository;
    private final BeneficiaryRepository beneficiaryRepository;

    public List<Aid> getAllAidsSorted() {
        return aidRepository.findAllOrderByPaymentDateDesc();
    }

    public Aid saveAid(Aid aid) {
        String email = aid.getBeneficiary().getEmail();
        Beneficiary beneficiary = beneficiaryRepository.findByEmail(email).orElse(null);

        if (beneficiary == null) {
            beneficiary = aid.getBeneficiary();
            beneficiary = beneficiaryRepository.save(beneficiary);
        }

        aid.setBeneficiary(beneficiary);

        return aidRepository.save(aid);
    }

    public Double getTotalAidAmount() {
        return aidRepository.getTotalAidAmount();
    }
}
