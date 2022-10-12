package br.com.api.taxpayer.taxpayer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.models.Company;
import br.com.api.taxpayer.taxpayer.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> listAll() {
        return companyRepository.findAll();
    }

    public ResponseEntity<?> registerCompany(Company company) {
        return new ResponseEntity<Company>(companyRepository.save(company), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCompany(Company company) {
        return new ResponseEntity<Company>(companyRepository.save(company), HttpStatus.OK);
    }

    public ResponseEntity<String> removeCompany(String id) {
        Long longId = Long.parseLong(id);
        companyRepository.deleteById(longId);

        return new ResponseEntity<String>("Produto " + id + " removido com sucesso", HttpStatus.OK);
    }

}
