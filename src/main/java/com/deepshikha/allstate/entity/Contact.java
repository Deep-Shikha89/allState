package com.deepshikha.allstate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "contact",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "contact", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        phoneNumbers.forEach( p -> p.setContact(this));
        this.phoneNumbers = phoneNumbers;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        addresses.forEach(a -> a.setContact(this));
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", addresses=" + addresses +
                '}';
    }
}
