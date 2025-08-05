package com.hei.school.repository;

import com.hei.school.model.Donation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long> {

  @Query("SELECT d FROM Donation d ORDER BY d.payment.paymentDate DESC")
  List<Donation> findAllOrderByPaymentDateDesc();

  @Query("SELECT SUM(d.payment.amount) FROM Donation d")
  Double getTotalDonatedAmount();
}
