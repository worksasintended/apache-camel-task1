package com.example.demo.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.OneToMany;

import java.util.ArrayList;
import java.util.List;

@CsvRecord(separator = ";", generateHeaderColumns = true, endWithLineBreak = false)
public class PhoneBook {
    @OneToMany
    private List<PhoneBookEntry> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<PhoneBookEntry>();
    }

    public List<PhoneBookEntry> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<PhoneBookEntry> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void addPhoneBookEntry(PhoneBookEntry phoneBookEntry) {
        this.phoneBook.add(phoneBookEntry);
    }
}
