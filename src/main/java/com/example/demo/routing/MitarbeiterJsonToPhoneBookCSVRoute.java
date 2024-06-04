package com.example.demo.routing;

import com.example.demo.domain.Employee;
import com.example.demo.domain.Employees;
import com.example.demo.domain.PhoneBook;
import com.example.demo.domain.PhoneBookEntry;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

@Component
public class MitarbeiterJsonToPhoneBookCSVRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data?moveFailed=fail&move=success")
                .unmarshal(new JacksonDataFormat(Employees.class))
                .process(this::processEmployeesToPhoneBook)
                .marshal(new BindyCsvDataFormat(PhoneBook.class))
                .to("file:data?fileName=out/${file:onlyname.noext}.csv");
    }

    private void processEmployeesToPhoneBook(Exchange exchange) throws Exception {
        Employees employees = exchange.getIn().getBody(Employees.class);
        PhoneBook phoneBook = new PhoneBook();
        // read employees and create phonebook
        for (Employee employee : employees.getEmployees()) {
            PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
            phoneBookEntry.setId(employee.getId());
            phoneBookEntry.setPhoneNumber(employee.getPhone());
            String name = employee.getFirstName() + " " + employee.getLastName();
            phoneBookEntry.setName(name);
            phoneBook.addPhoneBookEntry(phoneBookEntry);
        }
        exchange.getIn().setBody(phoneBook);
    }
}

