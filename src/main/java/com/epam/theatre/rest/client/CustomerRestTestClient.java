package com.epam.theatre.rest.client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.epam.theatre.domain.Customer;

@Component
public class CustomerRestTestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger(CustomerRestTestClient.class);


    private static final String URI = "http://localhost:8080/mvc";

    public void getAllUsers() {
    	LOGGER.info("Testing getAllUsers API");

        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String, Object>> customers = restTemplate.getForObject(URI+"/customers", List.class);

        if(customers!=null){
            for (LinkedHashMap<String, Object> map : customers) {
            	LOGGER.info("Customer : customerId=" + map.get("customerId") + ", firstName=" + map.get("firstName") + ", lastName=" + map.get("lastName") + ", email=" + map.get("email")
                        + ", birthDay=" + map.get("birthDay") + ", role=" + map.get("role"));
            }
        }else{
        	LOGGER.info("No customer exist");
        }
    }

    public Customer getUser(String id) {
    	LOGGER.info("Testing getUser API");
        Customer customer = restTemplate.getForObject(URI+"/customers/" +id, Customer.class);
        LOGGER.info(customer.toString());
        return customer;
    }

    public void createUser(Customer customer) {
    	LOGGER.info("Testing create User API");
        URI uri = restTemplate.postForLocation(URI+"/customers", customer, Customer.class);
        LOGGER.info("Location : "+uri.toASCIIString());
    }

    public void updateUser(Customer customer) {
    	LOGGER.info("Testing update User API");
        restTemplate.put(URI+"/customers/update/" + customer.getCustomerId(), customer);
        LOGGER.info(customer.toString());
    }

    public void deleteUser(Customer customer) {
    	LOGGER.info("Testing delete User API");
        restTemplate.delete(URI+"/customers/" + customer.getCustomerId());
    }

}
