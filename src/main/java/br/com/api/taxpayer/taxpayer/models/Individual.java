package br.com.api.taxpayer.taxpayer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "individuals")
@Getter
@Setter
public class Individual extends TaxPayer{

    private Double healthExpenditures;

    private Double taxPaid;

    public Individual(Long id, String name, String email, Double anualIncoming, String password, Double healthExpenditures, Double taxPaid) {
        super(id, name, email, anualIncoming, password);
        this.healthExpenditures = healthExpenditures;
        this.taxPaid = taxPaid;
    }

}
