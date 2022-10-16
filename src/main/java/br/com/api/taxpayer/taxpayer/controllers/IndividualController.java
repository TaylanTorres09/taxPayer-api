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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.taxpayer.taxpayer.models.Individual;
import br.com.api.taxpayer.taxpayer.models.components.UpdateIndividual;
import br.com.api.taxpayer.taxpayer.services.IndividualService;

@RestController
@RequestMapping("/individual")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @GetMapping
    public Iterable<Individual> listAll() {
        return individualService.listAll();
    }

    @GetMapping("/{name}")
    public Iterable<Individual> findByName(@PathVariable String name) {
        return individualService.findByName(name);
    }

    @PostMapping("/register")
    public ResponseEntity<?> resgisterIndividual(@Valid @RequestBody Individual individual, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }
        return individualService.registerIndividual(individual);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIndividual(@Valid @RequestBody UpdateIndividual updateIndividual, @PathVariable String id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }

        return individualService.updateIndividual(updateIndividual, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeIndividual(@PathVariable String id) {
        return individualService.removeIndividual(id);
    }
    
}
