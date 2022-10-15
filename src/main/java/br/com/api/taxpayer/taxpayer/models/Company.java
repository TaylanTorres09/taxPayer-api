package br.com.api.taxpayer.taxpayer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company extends TaxPayer {

    private Integer numbersOfEmployees;
    
    private Double taxPaid;

    public Company(Long id, String name, String email, Double anualIncoming, Integer numbersOfEmployees, Double taxPaid) {
        super(id, name, email, anualIncoming);
        this.numbersOfEmployees = numbersOfEmployees;
        this.taxPaid = taxPaid;
    }
    
}
