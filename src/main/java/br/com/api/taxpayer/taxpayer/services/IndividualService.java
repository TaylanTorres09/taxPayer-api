package br.com.api.taxpayer.taxpayer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.models.Individual;
import br.com.api.taxpayer.taxpayer.repositories.IndividualRepository;

@Service
public class IndividualService {
    
    @Autowired
    private IndividualRepository ir;

    public Iterable<Individual> listAll() {
        return ir.findAll();
    }

    public ResponseEntity<?> registerIndividual(Individual individual) {
        return new ResponseEntity<Individual>(ir.save(individual), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateIndividual(Individual individual) {
        return new ResponseEntity<Individual>(ir.save(individual), HttpStatus.OK);
    }

    public ResponseEntity<String> removeIndividual(String id) {
        Long longId = Long.parseLong(id);
        ir.deleteById(longId);

        return new ResponseEntity<String>("Produto " + id + " removido com sucesso", HttpStatus.OK);
    }

}
