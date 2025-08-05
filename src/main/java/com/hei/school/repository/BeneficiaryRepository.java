package com.hei.school.repository;

import com.hei.school.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByEmail(String email);
}
