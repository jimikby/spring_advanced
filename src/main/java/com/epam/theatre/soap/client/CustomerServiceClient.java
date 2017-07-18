package com.epam.theatre.soap.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epam.theatre.domain.Customer;
import com.epam.theatre.domain.Ticket;
import com.epam.theatre.soap.event.GetAll;
import com.epam.theatre.soap.customer.Delete;
import com.epam.theatre.soap.customer.GetAllByName;
import com.epam.theatre.soap.customer.GetAllByNameResponse;
import com.epam.theatre.soap.customer.GetAllResponse;
import com.epam.theatre.soap.customer.GetBookedTickets;
import com.epam.theatre.soap.customer.GetBookedTicketsResponse;
import com.epam.theatre.soap.customer.GetByEmail;
import com.epam.theatre.soap.customer.GetByEmailResponse;
import com.epam.theatre.soap.customer.GetById;
import com.epam.theatre.soap.customer.GetByIdResponse;
import com.epam.theatre.soap.customer.Save;
import com.epam.theatre.soap.customer.SaveAll;
import com.epam.theatre.soap.customer.Update;


@Component
public class CustomerServiceClient {

	 private static final Logger LOGGER = LogManager.getLogger(CustomerServiceClient.class);
	 
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void save(Customer customer) {
        Save save = new Save();
        save.setArg0(customer);
        webServiceTemplate.marshalSendAndReceive(save);
    }

    public void delete(Customer customer) {
        Delete delete = new Delete();
        delete.setArg0(customer);
        webServiceTemplate.marshalSendAndReceive(delete);
    }

    public Customer getById(Long id) {
        GetById getById = new GetById();
        getById.setArg0(id);
        GetByIdResponse getByIdResponse = (GetByIdResponse) webServiceTemplate.marshalSendAndReceive(getById);
        Customer customer = getByIdResponse.getReturn();
        LOGGER.info("SOAP result: " + customer.toString());
        return customer;
    }

    public Customer getByEmail(String email) {
        GetByEmail getByEmail = new GetByEmail();
        getByEmail.setArg0(email);
        GetByEmailResponse getByEmailResponse = (GetByEmailResponse) webServiceTemplate.marshalSendAndReceive(getByEmail);
        Customer customer = getByEmailResponse.getReturn();
        return customer;
    }

    public List<Customer> getAllByName() {
        GetAllByName getAllByName = new GetAllByName();
        GetAllByNameResponse getAllByNameResponse = (GetAllByNameResponse) webServiceTemplate.marshalSendAndReceive(getAllByName);
        List<Customer> customers = getAllByNameResponse.getReturn();
        return customers;
    }

    public List<Ticket> getBookedTickets(Customer customer) {
        GetBookedTickets getBookedTickets = new GetBookedTickets();
        getBookedTickets.setArg0(customer);
        GetBookedTicketsResponse getBookedTicketsResponse = (GetBookedTicketsResponse) webServiceTemplate
                .marshalSendAndReceive(getBookedTickets);
        List<Ticket> tickets = getBookedTicketsResponse.getReturn();
        return tickets;
    }

    public List<Customer> getAll() {
        GetAll getAll = new GetAll();
        GetAllResponse getAllResponse = (GetAllResponse) webServiceTemplate.marshalSendAndReceive(getAll);
        List<Customer> customers = getAllResponse.getReturn();
        return customers;
    }

    public void update(Customer customer) {
        Update update = new Update();
        update.setArg0(customer);
        webServiceTemplate.marshalSendAndReceive(update);
    }

    public void saveAll(List<Customer> customers) {
        SaveAll saveAll = new SaveAll();
        saveAll.setArg0(customers);
        webServiceTemplate.marshalSendAndReceive(saveAll);
    }
}
