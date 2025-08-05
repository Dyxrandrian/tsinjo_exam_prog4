package com.hei.school.repository;

import com.hei.school.model.Aid;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AidRepository extends JpaRepository<Aid, Long> {

  @Query("SELECT a FROM Aid a ORDER BY a.payment.paymentDate DESC")
  List<Aid> findAllOrderByPaymentDateDesc();

  @Query("SELECT SUM(a.payment.amount) FROM Aid a")
  Double getTotalAidAmount();
}
