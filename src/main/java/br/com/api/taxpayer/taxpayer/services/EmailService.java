package br.com.api.taxpayer.taxpayer.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.enums.StatusEmail;
import br.com.api.taxpayer.taxpayer.enums.WhoTaxPayer;
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

    @Value("${spring.mail.username}")
	private String emailFrom;

    public ResponseEntity<?> sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        email.setEmailFrom(emailFrom);
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());

            String whoTaxPayer = email.getWhoTaxPayer() != null ? email.getWhoTaxPayer().toString() : "";
            switch (whoTaxPayer) {
                case "COMPANY":
                    List<Company> company = companyService.findByName(email.getOwnerRef());
                    String taxCompany = company.isEmpty() ? "Reveja o nome do proprietário" : company.get(0).getTaxPaid().toString();
                    message.setText("Imposto de Renda Total: " + taxCompany);
                    break;
                case "INDIVIDUAL":
                    List<Individual> individual = individualService.findByName(email.getOwnerRef());
                    String taxIndividual = individual.isEmpty() ? "Reveja o nome do proprietário" : individual.get(0).getTaxPaid().toString();
                    message.setText("Imposto de Renda Total: " + taxIndividual);
                    break;
                default:
                    message.setText("OBS: Reveja o nome do proprietário.");
            }

            email.setText(message.getText());

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
