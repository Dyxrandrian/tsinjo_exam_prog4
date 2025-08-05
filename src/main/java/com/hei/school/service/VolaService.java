package com.hei.school.service;

import com.hei.school.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VolaService {

  private final RestTemplate restTemplate;
  private final String apiKey;

  public VolaService(RestTemplate restTemplate, @Value("${api.key}") String apiKey) {
    this.restTemplate = restTemplate;
    this.apiKey = apiKey;
  }

  public PaymentDto checkPayment(String payerEmail, String pspType, String pspPaymentId) {
    String url = "https://api.vola.mg/payments?"
            + "apiKey=" + apiKey
            + "&payerEmail=" + payerEmail
            + "&pspType=" + pspType
            + "&pspPaymentId=" + pspPaymentId;

    return restTemplate.getForEntity(url, PaymentDto.class).getBody();
  }
}

