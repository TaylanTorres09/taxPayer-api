package br.com.api.taxpayer.taxpayer.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.models.Company;
import br.com.api.taxpayer.taxpayer.models.components.UpdateCompany;
import br.com.api.taxpayer.taxpayer.repositories.CompanyRepository;
import br.com.api.taxpayer.taxpayer.utils.Regex;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Regex regex;

    public Iterable<Company> listAll() {
        return companyRepository.findAll();
    }

    public ResponseEntity<?> registerCompany(Company company) {

        if (companyRepository.existsByName(company.getName())) {
            return new ResponseEntity<String>("Nome existente", HttpStatus.BAD_REQUEST);
        } else if (companyRepository.existsByEmail(company.getEmail())) {
            return new ResponseEntity<String>("Email existente", HttpStatus.BAD_REQUEST);
        }

        // Validate e-mail
        Pattern patternEmail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(company.getEmail());
        // Validate Password
        Pattern patternPass = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matchPass = patternPass.matcher(company.getPassword());

        if(!matchEmail.find()) {
            String message = regex.regexEmail();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else if(!matchPass.find()){
            String message = regex.regexPassowrd();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        if(company.getNumbersOfEmployees() > 10) {
            company.setTaxPaid(company.getNumbersOfEmployees() * 0.14);
        } else {
            company.setTaxPaid(company.getNumbersOfEmployees() * 0.16);
        }

        return new ResponseEntity<Company>(companyRepository.save(company), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCompany(UpdateCompany updateCompany, String id) {
        Long longId = Long.parseLong(id);
        Company company = companyRepository.getReferenceById(longId);

        company.setAnualIncoming(updateCompany.getAnualIncoming());
        company.setNumbersOfEmployees(updateCompany.getNumbersOfEmployees());;

        return new ResponseEntity<Company>(companyRepository.save(company), HttpStatus.OK);
    }

    public ResponseEntity<String> removeCompany(String id) {
        Long longId = Long.parseLong(id);
        companyRepository.deleteById(longId);

        return new ResponseEntity<String>("Produto " + id + " removido com sucesso", HttpStatus.OK);
    }

}
