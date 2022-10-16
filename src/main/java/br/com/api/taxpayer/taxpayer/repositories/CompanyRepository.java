package br.com.api.taxpayer.taxpayer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.taxpayer.taxpayer.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
    
}
