package br.com.api.taxpayer.taxpayer.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.enums.StatusEmail;
import br.com.api.taxpayer.taxpayer.models.Email;
import br.com.api.taxpayer.taxpayer.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<?> sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            return new ResponseEntity<Email>(emailRepository.save(email), HttpStatus.CREATED);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
            return new ResponseEntity<StatusEmail>(StatusEmail.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

}