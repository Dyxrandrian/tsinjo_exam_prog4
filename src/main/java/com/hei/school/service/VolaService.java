package com.hei.school.service;

import com.hei.school.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VolaService {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    private final String baseUrl = "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws";

    public VolaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentDto checkPayment(String payerEmail, String pspType, String pspPaymentId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/payment")
                .queryParam("apiKey", apiKey)
                .queryParam("payerEmail", payerEmail)
                .queryParam("pspType", pspType)
                .queryParam("pspPaymentId", pspPaymentId)
                .toUriString();

        return restTemplate.getForObject(url, PaymentDto.class);
    }
}
