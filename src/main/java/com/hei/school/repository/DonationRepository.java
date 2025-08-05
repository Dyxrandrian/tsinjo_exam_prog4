package com.hei.school.repository;

import com.hei.school.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {
    List<Donation> findByAidId(String aidId);
// Récupère tous les dons associés à une aide donnée

    List<Donation> findByDateBetween(LocalDate startDate, LocalDate endDate);
// Récupère tous les dons dans une période

    List<Donation> findByDonorNameContainingIgnoreCase(String name);
// Recherche floue par nom de donateur

    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.aid.id = :aidId")
    BigDecimal getTotalDonatedAmountByAidId(@Param("aidId") Long aidId);
// Total des dons pour une aide précise

}
