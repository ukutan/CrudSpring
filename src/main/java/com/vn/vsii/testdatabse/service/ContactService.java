package com.vn.vsii.testdatabse.service;

import com.vn.vsii.testdatabse.model.Contact;
import java.util.List;

public interface ContactService {
    Iterable<Contact>findAll();
    List<Contact> search(String term);
    Contact findById(Integer id);
    void save(Contact contact);
    void delete(Integer id);

}
