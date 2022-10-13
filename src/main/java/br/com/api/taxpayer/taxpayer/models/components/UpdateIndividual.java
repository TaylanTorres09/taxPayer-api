package br.com.api.taxpayer.taxpayer.models.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UpdateIndividual {

    @NotNull
    private Double anualIncoming;

    private Double healthExpenditures;

}
