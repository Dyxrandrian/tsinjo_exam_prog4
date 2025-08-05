package com.hei.school.dto;

import java.time.Instant;
import lombok.Data;

@Data
public class PaymentDto {

  private String id;
  private PspPaymentDto pspPayment;
  private Instant creationInstant;
  private Instant lastPspVerificationInstant;
  private Integer verificationAttemptNb;
  private UserDto payer;
  private ApplicationDto application;
  private VerificationStatus verificationStatus;

  public enum VerificationStatus {
    VERIFYING,
    SUCCEEDED,
    FAILED
  }
}
