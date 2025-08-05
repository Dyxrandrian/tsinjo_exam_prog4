package com.hei.school.repository;

import com.hei.school.model.Aid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AidRepository extends JpaRepository<Aid, String> {
    List<Aid> findByBeneficiaryContainingIgnoreCase(String name);
    // Recherche floue par nom de bénéficiaire

    List<Aid> findByStartDateBeforeAndEndDateAfter(LocalDate date1, LocalDate date2);
    // Trouver les aides actives sur une période

    @Query("SELECT a FROM Aid a WHERE a.startDate <= :today AND a.endDate >= :today")
    List<Aid> findOngoingAids(@Param("today") LocalDate today);
    // Aides en cours aujourd’hui

    boolean existsByBeneficiary(String beneficiary);
    // Vérifie si une aide existe déjà pour un bénéficiaire

}
