package br.com.api.taxpayer.taxpayer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDto {
    
    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String emailTo;

    @NotBlank
    private String subject;

}
