package com.hei.school.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PspPaymentDto {
    private String pspType;  // ex: "ORANGE_MONEY"
    private String id;       // id du paiement PSP
    private Integer amount;
    private Instant creationInstant;// getters / setters
}
