package br.com.api.taxpayer.taxpayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.taxpayer.taxpayer.models.Individual;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long>{
    
}
