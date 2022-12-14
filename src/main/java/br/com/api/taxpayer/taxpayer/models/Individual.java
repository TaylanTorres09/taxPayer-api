package br.com.api.taxpayer.taxpayer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "individuals")
@Getter
@Setter
public class Individual extends TaxPayer{

    private Double healthExpenditures;

    private Double taxPaid;

    public Individual(Long id, String name, String email, Double anualIncoming, Double healthExpenditures, Double taxPaid) {
        super(id, name, email, anualIncoming);
        this.healthExpenditures = healthExpenditures;
        this.taxPaid = taxPaid;
    }

}
