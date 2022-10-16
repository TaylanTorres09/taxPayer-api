package br.com.api.taxpayer.taxpayer.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.el.ELManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.enums.StatusEmail;
import br.com.api.taxpayer.taxpayer.models.Company;
import br.com.api.taxpayer.taxpayer.models.Email;
import br.com.api.taxpayer.taxpayer.models.Individual;
import br.com.api.taxpayer.taxpayer.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private IndividualService individualService;

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<?> sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("taylan.job@gmail.com");
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());

           if(email.getWhoTaxPayer().toString().equals("COMPANY")) {
                List<Company> company = companyService.findByName(email.getOwnerRef());
                message.setText("Imposto de Renda Total: " + company.get(0).getTaxPaid().toString());
            } else if(email.getWhoTaxPayer().toString().equals("INDIVIDUAL")) {
                List<Individual> individual = individualService.findByName(email.getOwnerRef());
                message.setText("Imposto de Renda Total: " + individual.get(0).getTaxPaid().toString());
            } else {
                message.setText("OBS: Reveja o nome do proprietário.");
            }

            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            return new ResponseEntity<Email>(emailRepository.save(email), HttpStatus.CREATED);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
            return new ResponseEntity<StatusEmail>(StatusEmail.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<Email> findAll() {
        return emailRepository.findAll();
    }

}
