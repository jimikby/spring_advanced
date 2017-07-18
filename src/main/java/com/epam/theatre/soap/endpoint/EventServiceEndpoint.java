package com.epam.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.theatre.service.EventService;

import com.epam.theatre.soap.event.Delete;
import com.epam.theatre.soap.event.DeleteResponse;
import com.epam.theatre.soap.event.GetAll;
import com.epam.theatre.soap.event.GetAllResponse;
import com.epam.theatre.soap.event.GetById;
import com.epam.theatre.soap.event.GetByIdResponse;
import com.epam.theatre.soap.event.GetByName;
import com.epam.theatre.soap.event.GetByNameResponse;
import com.epam.theatre.soap.event.Save;
import com.epam.theatre.soap.event.SaveAll;
import com.epam.theatre.soap.event.SaveAllResponse;
import com.epam.theatre.soap.event.SaveResponse;
import com.epam.theatre.soap.event.Update;
import com.epam.theatre.soap.event.UpdateResponse;


@Endpoint
public class EventServiceEndpoint {

    private static final String NAMESPACE_URI = "http://event.impl.service.theatre.epam.com/";

    @Autowired
    private EventService eventService;

    @PayloadRoot(localPart = "save", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveResponse saveEvent(@RequestPayload Save save) {
        SaveResponse saveResponse = new SaveResponse();
        eventService.save(save.getArg0());
        return saveResponse;
    }

    @PayloadRoot(localPart = "delete", namespace = NAMESPACE_URI)
    @ResponsePayload
    public DeleteResponse deleteEvent(@RequestPayload Delete delete) {
        DeleteResponse deleteResponse = new DeleteResponse();
        eventService.remove(delete.getArg0().getEventId());
        return deleteResponse;
    }

    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByIdResponse getEventById(@RequestPayload GetById getById) {
        GetByIdResponse getByIdResponse = new GetByIdResponse();
        getByIdResponse.setReturn(eventService.getById(getById.getArg0()));
        return getByIdResponse;
    }

    @PayloadRoot(localPart = "getByName", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByNameResponse getByName(@RequestPayload GetByName getByName) {
        GetByNameResponse getByNameResponse = new GetByNameResponse();
        getByNameResponse.setReturn(eventService.getByName(getByName.getArg0()));
        return getByNameResponse ;
    }

    @PayloadRoot(localPart = "getAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllResponse getAllEvents(@RequestPayload GetAll getAll) {
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setReturn(eventService.getAll());
        return getAllResponse ;
    }

    @PayloadRoot(localPart = "update", namespace = NAMESPACE_URI)
    @ResponsePayload
    public UpdateResponse updateEvent(@RequestPayload Update update) {
        UpdateResponse updateResponse = new UpdateResponse();
        eventService.update(update.getArg0().getEventId(), update.getArg0());
        return updateResponse;
    }

    @PayloadRoot(localPart = "saveAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveAllResponse saveAllEvents(@RequestPayload SaveAll saveAll) {
        SaveAllResponse saveAllResponse = new SaveAllResponse();
        eventService.saveAll(saveAll.getArg0());
        return saveAllResponse;
    }

}
