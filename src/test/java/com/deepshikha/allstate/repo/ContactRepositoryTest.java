package com.deepshikha.allstate.repo;

import com.deepshikha.allstate.entity.Address;
import com.deepshikha.allstate.entity.Contact;
import com.deepshikha.allstate.entity.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public  void testSaveContact(){
        Contact c1 = new Contact();
        c1.setFirstName("first");
        c1.setLastName("last");
        PhoneNumber phoneNumber1 = new PhoneNumber("+44", "1234567890");
        PhoneNumber phoneNumber2 = new PhoneNumber("+44", "1234567890");
        Address address1 = new Address();
        address1.setAddressLine1("7/8 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        Address address2 = new Address();
        address1.setAddressLine1("1/3 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        Address address3 = new Address();
        address1.setAddressLine1("5/6 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        c1.setPhoneNumbers(Set.of(phoneNumber1,phoneNumber2));
        c1.setAddresses(Set.of(address1,address2,address3));
        contactRepository.save(c1);
        assertNotEquals(0,c1.getId());
        assertNotEquals(0,phoneNumber1.getId());
        assertNotEquals(0,phoneNumber2.getId());
        assertNotEquals(0,address1.getId());
        assertNotEquals(0,address2.getId());
        assertNotEquals(0,address3.getId());
    }


    @Test
    public  void testDeleteContact(){
        Contact c1 = new Contact();
        c1.setFirstName("first");
        c1.setLastName("last");
        PhoneNumber phoneNumber1 = new PhoneNumber("+44", "1234567890");
        PhoneNumber phoneNumber2 = new PhoneNumber("+44", "1234567890");
        Address address1 = new Address();
        address1.setAddressLine1("7/8 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        c1.setPhoneNumbers(Set.of(phoneNumber1,phoneNumber2));
        c1.setAddresses(Set.of(address1));
        testEntityManager.persistAndFlush(c1);
        assertTrue(contactRepository.findAll().iterator().hasNext());

        contactRepository.deleteById(c1.getId());
        assertFalse(contactRepository.findAll().iterator().hasNext());

    }


    @Test
    public  void testGetAll(){
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
        testEntityManager.persistAndFlush(c1);
        testEntityManager.persistAndFlush(c2);
        assertEquals(2,StreamSupport.stream(contactRepository.findAll().spliterator()
                ,false).count());

    }

    @Test
    public  void testFindByFirstName(){
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
        testEntityManager.persistAndFlush(c1);
        testEntityManager.persistAndFlush(c2);
        assertEquals(1,StreamSupport.stream(contactRepository.findByFirstName("first").spliterator()
                ,false).count());

    }


    @Test
    public  void testFindByLastName(){
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
        testEntityManager.persistAndFlush(c1);
        testEntityManager.persistAndFlush(c2);
        assertEquals(1,StreamSupport.stream(contactRepository.findByLastName("last").spliterator()
                ,false).count());

    }


    @Test
    public  void testFindByFirstNameLastName(){
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
        testEntityManager.persistAndFlush(c1);
        testEntityManager.persistAndFlush(c2);
        assertEquals(1,
                StreamSupport.stream(contactRepository.findByFirstNameAndLastName("first","last").spliterator()
                ,false).count());

    }
}