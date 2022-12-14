package br.com.api.taxpayer.taxpayer.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.taxpayer.taxpayer.models.Individual;
import br.com.api.taxpayer.taxpayer.models.components.UpdateIndividual;
import br.com.api.taxpayer.taxpayer.repositories.IndividualRepository;
import br.com.api.taxpayer.taxpayer.utils.Regex;

@Service
public class IndividualService {
    
    @Autowired
    private IndividualRepository ir;

    @Autowired
    private Regex regex;

    public Iterable<Individual> listAll() {
        return ir.findAll();
    }

    public List<Individual> findByName(String name) {
        return ir.findByName(name);
    }

    public ResponseEntity<?> registerIndividual(Individual individual) {

        if (ir.existsByName(individual.getName())) {
            return new ResponseEntity<String>("Nome existente", HttpStatus.BAD_REQUEST);
        } else if (ir.existsByEmail(individual.getEmail())) {
            return new ResponseEntity<String>("Email existente", HttpStatus.BAD_REQUEST);
        }

        // Validate e-mail
        Pattern patternEmail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(individual.getEmail());

        if(!matchEmail.find()) {
            String message = regex.regexEmail();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        Double healthTax = individual.getHealthExpenditures() * 0.5;
        if(individual.getAnualIncoming() < 20000.00) {
            individual.setTaxPaid(individual.getAnualIncoming() * 0.15 - healthTax);
        } else {
            individual.setTaxPaid(individual.getAnualIncoming() * 0.25 - healthTax);
        }
        return new ResponseEntity<Individual>(ir.save(individual), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateIndividual(UpdateIndividual updateIndividual, String id) {
        Long longId = Long.parseLong(id);
        Individual individual = ir.getReferenceById(longId);

        individual.setAnualIncoming(updateIndividual.getAnualIncoming());
        individual.setHealthExpenditures(updateIndividual.getHealthExpenditures());

        Double healthTax = individual.getHealthExpenditures() * 0.5;
        if(individual.getAnualIncoming() < 20000.00) {
            individual.setTaxPaid(individual.getAnualIncoming() * 0.15 - healthTax);
        } else {
            individual.setTaxPaid(individual.getAnualIncoming() * 0.25 - healthTax);
        }

        return new ResponseEntity<Individual>(ir.save(individual), HttpStatus.OK);
    }

    public ResponseEntity<String> removeIndividual(String id) {
        Long longId = Long.parseLong(id);
        ir.deleteById(longId);

        return new ResponseEntity<String>("Produto " + id + " removido com sucesso", HttpStatus.OK);
    }

}
