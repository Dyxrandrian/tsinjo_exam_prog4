package tsinjoTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.hei.school.dto.PaymentDto;
import com.hei.school.service.VolaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

class VolaServiceTest {

  @Mock private RestTemplate restTemplate;

  @InjectMocks private VolaService volaService;

  @Value("${api.key}")
  private String apiKey;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    // Injecter la clé API dans volaService (champ privé)
    ReflectionTestUtils.setField(volaService, "apiKey", apiKey);
  }

  @Test
  void checkPayment_shouldCallCorrectUrlAndReturnPaymentDto() {
    // Arrange
    String payerEmail = "test@example.com";
    String pspType = "ORANGE_MONEY";
    String pspPaymentId = "PAY123";

    PaymentDto mockPaymentDto = new PaymentDto();
    mockPaymentDto.setId("payment-1");

    ResponseEntity<PaymentDto> mockResponse = new ResponseEntity<>(mockPaymentDto, HttpStatus.OK);

    when(restTemplate.getForEntity(anyString(), eq(PaymentDto.class))).thenReturn(mockResponse);

    // Act
    PaymentDto result = volaService.checkPayment(payerEmail, pspType, pspPaymentId);

    // Assert
    assertNotNull(result);
    assertEquals("payment-1", result.getId());

    ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
    verify(restTemplate).getForEntity(urlCaptor.capture(), eq(PaymentDto.class));
    String calledUrl = urlCaptor.getValue();

    assertTrue(calledUrl.contains("apiKey=" + apiKey));
    assertTrue(calledUrl.contains("payerEmail=" + payerEmail));
    assertTrue(calledUrl.contains("pspType=" + pspType));
    assertTrue(calledUrl.contains("pspPaymentId=" + pspPaymentId));
  }
}
