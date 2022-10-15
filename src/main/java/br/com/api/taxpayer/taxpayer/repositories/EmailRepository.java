package br.com.api.taxpayer.taxpayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.taxpayer.taxpayer.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    
}
