package br.com.api.taxpayer.taxpayer.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.api.taxpayer.taxpayer.enums.StatusEmail;
import br.com.api.taxpayer.taxpayer.enums.WhoTaxPayer;
import lombok.Data;

@Data
@Entity
@Table(name = "emails")
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID emailId;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private WhoTaxPayer whoTaxPayer;

    private StatusEmail statusEmail;

}
