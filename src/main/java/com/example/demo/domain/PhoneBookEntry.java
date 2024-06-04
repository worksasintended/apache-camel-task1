package com.example.demo.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ";", generateHeaderColumns = true, endWithLineBreak = false)

public class PhoneBookEntry {
    @DataField(pos = 1, columnName = "ID")
    private String id;
    @DataField(pos = 2, columnName = "NAME")
    private String name;
    @DataField(pos = 3, columnName = "PHONENUMBER")
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
