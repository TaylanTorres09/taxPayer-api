package br.com.api.taxpayer.taxpayer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company extends TaxPayer {

    private Integer numbersOfEmployees;
    
    private Double taxPaid;

    public Company(Long id, String name, String email, Double anualIncoming, String password, Integer numbersOfEmployees, Double taxPaid) {
        super(id, name, email, anualIncoming, password);
        this.numbersOfEmployees = numbersOfEmployees;
        this.taxPaid = taxPaid;
    }
    
}
