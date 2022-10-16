package br.com.api.taxpayer.taxpayer.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.taxpayer.taxpayer.dto.EmailDto;
import br.com.api.taxpayer.taxpayer.enums.WhoTaxPayer;
import br.com.api.taxpayer.taxpayer.models.Email;
import br.com.api.taxpayer.taxpayer.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/sending")
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setWhoTaxPayer(WhoTaxPayer.valueOf(emailDto.getWhoTaxPayer()));
        return emailService.sendEmail(email);
    }

    @GetMapping("/listAll")
    public Iterable<Email> findAll() {
        return emailService.findAll();
    }

}
