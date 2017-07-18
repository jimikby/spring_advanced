package com.epam.theatre.rest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.theatre.domain.Customer;
import com.epam.theatre.domain.Ticket;
import com.epam.theatre.service.CustomerService;
import com.epam.theatre.service.TicketService;

@RestController
public class CustomersRestController {

	private static final Logger LOGGER = LogManager.getLogger(CustomersRestController.class);

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private TicketService ticketService;
    
    @ResponseBody
    @RequestMapping(value = "/customers", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.getAll();
        if(customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Customer> getById(@PathVariable String customerId) {
    	LOGGER.info("Getting Customer with id: " + customerId + " ...");
        Customer customer = customerService.getById(Long.parseLong(customerId));
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/customers/{customerId}/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getBookedTickets(@PathVariable String customerId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(Long.parseLong(customerId));
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {

        if (customerService.getById(customer.getCustomerId()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        customerService.save(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(customer.getCustomerId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {

        Customer currentCustomer = customerService.getById(Long.valueOf(id));

        if (currentCustomer==null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setBirthDay(customer.getBirthDay());
        currentCustomer.setPassword(customer.getPassword());
        currentCustomer.setRole(customer.getRole());


        customerService.updateById(currentCustomer.getCustomerId(), currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String id) {

        Customer customer = customerService.getById(Long.valueOf(id));
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        customerService.remove(customer.getCustomerId());
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
}
