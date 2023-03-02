package com.deepshikha.allstate.rest;

import com.deepshikha.allstate.entity.Contact;
import com.deepshikha.allstate.exceptions.NotFoundException;
import com.deepshikha.allstate.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts",produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactRest {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ContactRest.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Long> saveContacts(@RequestBody  List<Contact> contacts){
        return contactService.saveContacts(contacts);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact){
        contact.setId(id);
        LOGGER.info("Contact --" + contact);
        contactService.updateContact(contact);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable Long id){
        Contact contact = new Contact();
        contact.setId(id);
        List<Contact> result = contactService.getContacts(contact);
        if(result.size() ==1 ){
            return result.get(0);
        }else{
            throw  new NotFoundException("No contact found with id:: " +  id);
        }
    }

    @RequestMapping(path="/search",method = RequestMethod.GET)
    public List<Contact> searchContact(@RequestParam(value = "firstName",required = false) String firstName
                              , @RequestParam(value = "lastName",required = false) String lastName){
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        List<Contact> result = contactService.getContacts(contact);
        if(result.size() ==0 ){
            throw  new NotFoundException("No contact found with firstName:: " +  firstName +
                    " lastName ::" + lastName);
        }

        return result;
    }

}
