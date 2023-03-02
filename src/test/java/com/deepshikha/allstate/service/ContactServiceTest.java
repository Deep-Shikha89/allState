package com.deepshikha.allstate.service;

import com.deepshikha.allstate.entity.Address;
import com.deepshikha.allstate.entity.Contact;
import com.deepshikha.allstate.entity.PhoneNumber;
import com.deepshikha.allstate.exceptions.NotFoundException;
import com.deepshikha.allstate.repo.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService sut;

    @Test
    public void testSaveContacts(){
        Contact c1 = new Contact();
        c1.setFirstName("first");
        c1.setLastName("last");
        PhoneNumber phoneNumber1 = new PhoneNumber("+44", "1234567890");
        Address address1 = new Address();
        address1.setAddressLine1("7/8 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        c1.setPhoneNumbers(Set.of(phoneNumber1));
        c1.setAddresses(Set.of(address1));
        Contact c2 = new Contact();
        c2.setFirstName("first1");
        c2.setLastName("last1");
        PhoneNumber phoneNumber2 = new PhoneNumber("+44", "1234567890");
        Address address2 = new Address();
        address2.setAddressLine1("7/8 980");
        address2.setAddressLine2("St Edmund Road");
        address2.setCity("Glasgow");
        address2.setPincode("G86RG");
        address2.setCountry("UK");
        c2.setPhoneNumbers(Set.of(phoneNumber2));
        c2.setAddresses(Set.of(address2));

        List<Contact> argument = List.of(c1,c2);
        sut.saveContacts(argument);
        Mockito.verify(contactRepository,Mockito.times(2)).save(Mockito.any());
    }

    @Test
    public void testDeleteContacts(){
        sut.deleteContact(1);
        Mockito.verify(contactRepository).deleteById(1l);
    }


    @Test
    public void testGetContacts(){
        Contact contact = new Contact();
        contact.setId(1);
        List<Contact> contacts = List.of(contact);
        Mockito.when(contactRepository.findById(1l)).thenReturn(Optional.of(contact));
        List<Contact> result = sut.getContacts(contact);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetContactsFirstNameLastName(){
        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        List<Contact> contacts = List.of(contact);
        Mockito.when(contactRepository.findByFirstNameAndLastName("first","last"))
                .thenReturn(contacts);
        List<Contact> result = sut.getContacts(contact);
        assertEquals(1, result.size());
    }


    @Test
    public void testGetContactsFirstName(){
        Contact contact = new Contact();
        contact.setFirstName("first");
        List<Contact> contacts = List.of(contact);
        Mockito.when(contactRepository.findByFirstName("first"))
                .thenReturn(contacts);
        List<Contact> result = sut.getContacts(contact);
        assertEquals(1, result.size());
    }


    @Test
    public void testGetContactsLastName(){
        Contact contact = new Contact();
        contact.setLastName("last");
        List<Contact> contacts = List.of(contact);
        Mockito.when(contactRepository.findByLastName("last"))
                .thenReturn(contacts);
        List<Contact> result = sut.getContacts(contact);
        assertEquals(1, result.size());
    }


    @Test
    public void testGetContactNotFound(){
        Contact contact = new Contact();
        contact.setLastName("last");
        List<Contact> contacts = new ArrayList<>();
        Mockito.when(contactRepository.findByLastName("last"))
                .thenReturn(contacts);
        assertThrows(NotFoundException.class,()->sut.getContacts(contact));
    }

    @Test
    public void testUpdate(){
        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName("first");
        contact.setLastName("last");
        Mockito.when(contactRepository.findById(1l))
                .thenReturn(Optional.of(contact));
        sut.updateContact(contact);
        Mockito.verify(contactRepository,Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testUpdateNotFOund(){
        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName("first");
        contact.setLastName("last");
        Mockito.when(contactRepository.findById(1l))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> sut.updateContact(contact));
    }
}