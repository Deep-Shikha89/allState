package com.deepshikha.allstate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="phone_number")
public class PhoneNumber {

    public PhoneNumber() {
    }

    public PhoneNumber(String countryCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.number = phoneNumber;
    }

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;

    private String countryCode;

    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", phoneNumber='" + number + '\'' +
                '}';
    }
}
