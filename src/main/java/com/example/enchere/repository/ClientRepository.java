package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.enchere.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByTelephoneAndMotdepasse(String telephone, String motdepasse);
}
