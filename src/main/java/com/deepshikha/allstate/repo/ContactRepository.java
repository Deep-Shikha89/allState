package com.deepshikha.allstate.repo;

import com.deepshikha.allstate.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {

     Iterable<Contact> findByFirstNameAndLastName(String firstName, String lastName);

     Iterable<Contact> findByFirstName(String firstName);

     Iterable<Contact> findByLastName(String lastName);




}
