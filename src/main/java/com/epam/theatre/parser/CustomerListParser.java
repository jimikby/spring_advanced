package com.epam.theatre.parser;

import java.io.IOException;
import java.util.List;

import com.epam.theatre.domain.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;

public class CustomerListParser {

    public static List<Customer> getUserList(String data) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<Customer> users = Lists.newArrayList();
        if (!data.isEmpty()) {
            users = mapper.readValue(data,
                    TypeFactory.defaultInstance()
                    .constructCollectionType(
                            List.class,
                            Customer.class));
        }
        return users;
    }
}
