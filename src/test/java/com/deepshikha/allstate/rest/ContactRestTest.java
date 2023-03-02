package com.deepshikha.allstate.rest;

import com.deepshikha.allstate.entity.Address;
import com.deepshikha.allstate.entity.Contact;
import com.deepshikha.allstate.entity.PhoneNumber;
import com.deepshikha.allstate.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactRest.class)
class ContactRestTest {

    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user",password = "password")
    public void testGetAllContacts() throws Exception {
        RequestBuilder requst = MockMvcRequestBuilders.
                get("/contacts")
                .accept(MediaType.APPLICATION_JSON);
        Contact c1 = new Contact();
        c1.setFirstName("first");
        c1.setLastName("last");
        c1.setId(1);
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
        c2.setId(1);
        PhoneNumber phoneNumber2 = new PhoneNumber("+44", "1234567890");
        Address address2 = new Address();
        address2.setAddressLine1("7/8 980");
        address2.setAddressLine2("St Edmund Road");
        address2.setCity("Glasgow");
        address2.setPincode("G86RG");
        address2.setCountry("UK");
        c2.setPhoneNumbers(Set.of(phoneNumber2));
        c2.setAddresses(Set.of(address2));
        Mockito.when(contactService.getAllContacts()).thenReturn(List.of(c1,c2));
        mockMvc.perform(requst).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{\"id\":1,\"firstName\":\"first\",\"lastName\":\"last\",\"phoneNumbers\":[{\"countryCode\":\"+44\",\"number\":\"1234567890\"}],\"addresses\":[{\"addressLine1\":\"7/8 980\",\"addressLine2\":\"St Edmund Road\",\"city\":\"Glasgow\",\"country\":\"UK\",\"pincode\":\"G86RG\"}]},{\"id\":1,\"firstName\":\"first1\",\"lastName\":\"last1\",\"phoneNumbers\":[{\"countryCode\":\"+44\",\"number\":\"1234567890\"}],\"addresses\":[{\"addressLine1\":\"7/8 980\",\"addressLine2\":\"St Edmund Road\",\"city\":\"Glasgow\",\"country\":\"UK\",\"pincode\":\"G86RG\"}]}]"));


    }

    @Test
    @WithMockUser(username = "user",password = "password")
    public void testGetContactByIdNotFound() throws Exception {
        RequestBuilder requst = MockMvcRequestBuilders.
                get("/contacts/1")
                .accept(MediaType.APPLICATION_JSON);


        Mockito.when(contactService.getContacts(Mockito.any())).thenReturn(new ArrayList<>());
       // MvcResult mvcResult = mockMvc.perform(requst).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        mockMvc.perform(requst).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    @WithMockUser(username = "user",password = "password")
    public void testGetContactById() throws Exception {
        RequestBuilder requst = MockMvcRequestBuilders.
                get("/contacts/1")
                .accept(MediaType.APPLICATION_JSON);
        Contact c1 = new Contact();
        c1.setFirstName("first");
        c1.setLastName("last");
        c1.setId(1);
        PhoneNumber phoneNumber1 = new PhoneNumber("+44", "1234567890");
        Address address1 = new Address();
        address1.setAddressLine1("7/8 980");
        address1.setAddressLine2("St Edmund Road");
        address1.setCity("Glasgow");
        address1.setPincode("G86RG");
        address1.setCountry("UK");
        c1.setPhoneNumbers(Set.of(phoneNumber1));
        c1.setAddresses(Set.of(address1));

        Mockito.when(contactService.getContacts(Mockito.any())).thenReturn(List.of(c1));
        MvcResult mvcResult = mockMvc.perform(requst).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        mockMvc.perform(requst).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"id\":1,\"firstName\":\"first\",\"lastName\":\"last\",\"phoneNumbers\":[{\"countryCode\":\"+44\",\"number\":\"1234567890\"}],\"addresses\":[{\"addressLine1\":\"7/8 980\",\"addressLine2\":\"St Edmund Road\",\"city\":\"Glasgow\",\"country\":\"UK\",\"pincode\":\"G86RG\"}]}"));


    }



}