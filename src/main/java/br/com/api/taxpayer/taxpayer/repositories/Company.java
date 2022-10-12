package br.com.api.taxpayer.taxpayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Company extends JpaRepository<Company, Long> {
    
}
