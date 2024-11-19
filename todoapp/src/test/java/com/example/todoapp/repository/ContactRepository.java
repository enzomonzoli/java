package com.example.todoapp.repository;

import com.example.todoapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário.
}
