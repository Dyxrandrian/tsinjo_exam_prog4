package com.hei.school.endpoint.rest.controller;

import com.hei.school.dto.PaymentDto;
import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Payment;
import com.hei.school.service.AidService;
import com.hei.school.service.DonationService;
import com.hei.school.service.VolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TsinjoController {

  private final DonationService donationService;
  private final AidService aidService;
  private final VolaService volaService;

  @GetMapping("/")
  public String showHomePage(Model model) {
    model.addAttribute("donations", donationService.getAllDonationsSorted());
    model.addAttribute("aids", aidService.getAllAidsSorted());

    Donation donationForm = new Donation();
    donationForm.setDonor(new Donor());
    donationForm.setPayment(new Payment());
    model.addAttribute("donationForm", donationForm);

    return "index";
  }

  @PostMapping("/donations")
  public String submitDonation(@ModelAttribute("donationForm") Donation donation, Model model) {

    // Exemple : récupérer infos du paiement depuis le formulaire
    String payerEmail = donation.getDonor().getEmail();
    String pspType = donation.getPayment().getPspType();
    String pspPaymentId = donation.getPayment().getPspPaymentId();

    // Vérification paiement via API Vola
    PaymentDto paymentDto = volaService.checkPayment(payerEmail, pspType, pspPaymentId);

    if (paymentDto != null && "SUCCEEDED".equals(paymentDto.getVerificationStatus())) {
      // Paiement validé => sauvegarder la donation
      donationService.saveDonation(donation);
      return "redirect:/?success";
    } else {
      // Paiement invalide ou en cours => afficher erreur
      model.addAttribute("errorMessage", "Paiement non validé ou en attente.");
      model.addAttribute("donations", donationService.getAllDonationsSorted());
      return "index";
    }
  }
}
