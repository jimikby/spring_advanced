package com.epam.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.theatre.service.CustomerService;
import com.epam.theatre.soap.customer.Delete;
import com.epam.theatre.soap.customer.GetAll;
import com.epam.theatre.soap.customer.GetAllResponse;
import com.epam.theatre.soap.customer.GetByEmail;
import com.epam.theatre.soap.customer.GetByEmailResponse;
import com.epam.theatre.soap.customer.GetById;
import com.epam.theatre.soap.customer.GetByIdResponse;
import com.epam.theatre.soap.customer.Save;
import com.epam.theatre.soap.customer.SaveAll;
import com.epam.theatre.soap.customer.SaveAllResponse;
import com.epam.theatre.soap.customer.SaveResponse;
import com.epam.theatre.soap.event.DeleteResponse;

@Endpoint
public class UserServiceEndpoint {

    private static final String NAMESPACE_URI = "http://impl.service.theatre.epam.com/";

    @Autowired
    private CustomerService userService;

    @PayloadRoot(localPart = "save", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveResponse saveUser(@RequestPayload Save save) {
        SaveResponse saveResponse = new SaveResponse();
        userService.save(save.getArg0());
        return saveResponse;
    }

    @PayloadRoot(localPart = "delete", namespace = NAMESPACE_URI)
    @ResponsePayload
    public DeleteResponse deleteUser(@RequestPayload Delete delete) {
        DeleteResponse deleteResponse = new DeleteResponse();
        userService.remove(delete.getArg0().getCustomerId());
        return deleteResponse;
    }

    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByIdResponse getUserById(@RequestPayload GetById getById) {
        GetByIdResponse getByIdResponse = new GetByIdResponse();
        getByIdResponse.setReturn(userService.getById(getById.getArg0()));
        return getByIdResponse;
    }

    @PayloadRoot(localPart = "getByEmail", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByEmailResponse getByEmail(@RequestPayload GetByEmail getByEmail) {
        GetByEmailResponse getByEmailResponse = new GetByEmailResponse();
        getByEmailResponse.setReturn(userService.takeByEmail(getByEmail.getArg0()));
        return getByEmailResponse;
    }

    @PayloadRoot(localPart = "getAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllResponse getAllUsers(@RequestPayload GetAll getAll) {
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setReturn(userService.getAll());
        return getAllResponse;
    }


    @PayloadRoot(localPart = "saveAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveAllResponse saveAllUsers(@RequestPayload SaveAll saveAll) {
        SaveAllResponse saveAllResponse = new SaveAllResponse();
        userService.saveAll(saveAll.getArg0());
        return saveAllResponse;
    }
}
