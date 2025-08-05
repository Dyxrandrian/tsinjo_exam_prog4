package com.hei.school.controller;

import com.hei.school.model.Donation;
import com.hei.school.model.Aid;
import com.hei.school.service.DonationService;
import com.hei.school.service.AidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TsinjoController {

    private final DonationService donationService;
    private final AidService aidService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("donations", donationService.getAllDonationsSorted());
        model.addAttribute("aids", aidService.getAllAidsSorted());
        model.addAttribute("donationForm", new Donation());
        return "index";  // Thymeleaf template name: index.html
    }

    @PostMapping("/donations")
    public String submitDonation(@ModelAttribute("donationForm") Donation donation) {
        donationService.saveDonation(donation);
        return "redirect:/";
    }
}
