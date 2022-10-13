package br.com.api.taxpayer.taxpayer.models.components;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UpdateIndividual {

    private Double anualIncoming;

    private Double healthExpenditures;

}
