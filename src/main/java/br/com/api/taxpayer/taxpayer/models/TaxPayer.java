package br.com.api.taxpayer.taxpayer.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
public abstract class TaxPayer implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    
    @NotEmpty(message = "Email é obrigatório")
    private String email;

    @NotBlank
    private Double anualIncoming;

    @NotBlank
    private String password;

}
