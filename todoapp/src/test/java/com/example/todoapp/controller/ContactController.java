package com.example.todoapp.controller;

import com.example.todoapp.model.Contact;
import com.example.todoapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // Endpoint para criar um novo contato
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // Endpoint para listar todos os contatos
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Endpoint para buscar um contato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um contato
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {
        Optional<Contact> existingContact = contactRepository.findById(id);

        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setName(contactDetails.getName());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhoneNumber(contactDetails.getPhoneNumber());

            Contact updatedContact = contactRepository.save(contact);
            return ResponseEntity.ok(updatedContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para excluir um contato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
