package br.com.api.taxpayer.taxpayer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.taxpayer.taxpayer.models.Company;
import br.com.api.taxpayer.taxpayer.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public Iterable<Company> listAll() {
        return companyService.listAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> resgisterCompany(@Valid @RequestBody Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }
        return companyService.registerCompany(company);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company, @PathVariable String id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }
        Long longId = Long.parseLong(id);
        company.setId(longId);
        return companyService.updateCompany(company);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> removeCompany(@PathVariable String id) {
        return companyService.removeCompany(id);
    }
    
}
