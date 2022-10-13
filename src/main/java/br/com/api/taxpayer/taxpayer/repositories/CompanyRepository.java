package br.com.api.taxpayer.taxpayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.taxpayer.taxpayer.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
    
}
