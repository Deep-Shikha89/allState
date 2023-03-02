package com.deepshikha.allstate.service;

import com.deepshikha.allstate.entity.Contact;
import com.deepshikha.allstate.exceptions.NotFoundException;
import com.deepshikha.allstate.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public List<Long> saveContacts(List<Contact> contacts) {
        return contacts.stream().map(c -> saveContact(c)).collect(Collectors.toList());
    }

    private Long saveContact(Contact contact) {
        contact.getAddresses().forEach(a -> a.setContact(contact));
        contact.getPhoneNumbers().forEach(p -> p.setContact(contact));
        contactRepository.save(contact);
        return contact.getId();
    }

    public void deleteContact(long id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> getContacts(Contact contact) {
        List<Contact> result = new ArrayList<>();
        if (contact.getId() != 0) {
            result = contactRepository.findById(contact.getId())
                    .stream().toList();
        } else if (Objects.nonNull(contact.getFirstName()) && Objects.nonNull(contact.getLastName())) {
            result = StreamSupport.stream(contactRepository.
                            findByFirstNameAndLastName(contact.getFirstName(), contact.getLastName())
                            .spliterator(), false)
                    .collect(Collectors.toList());

        } else if (Objects.nonNull(contact.getFirstName())) {
            result = StreamSupport.stream(contactRepository
                            .findByFirstName(contact.getFirstName())
                            .spliterator(), false)
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(contact.getLastName())) {
            result = StreamSupport.stream(contactRepository
                            .findByLastName(contact.getLastName())
                            .spliterator(), false)
                    .collect(Collectors.toList());
        }

        if (result.isEmpty()) {
            throw new NotFoundException("Unable to find contact with provided input " + contact);
        }
        return result;
    }

    public List<Contact> getAllContacts(){
        return StreamSupport.stream(contactRepository
                .findAll().spliterator(),false).collect(Collectors.toList());
    }

    public void updateContact(Contact contact) {
        Optional<Contact> contactInDb = contactRepository.findById(contact.getId());
        if(contactInDb.isPresent()){
            contact.getAddresses().forEach(a -> a.setContact(contact));
            contact.getPhoneNumbers().forEach(p -> p.setContact(contact));
            contactRepository.save(contact);
        }else{
            throw new NotFoundException("No contact found with id::" + contact.getId());
        }

    }
}
