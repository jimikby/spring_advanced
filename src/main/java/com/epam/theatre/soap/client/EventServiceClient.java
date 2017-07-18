package com.epam.theatre.soap.client;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epam.theatre.domain.Event;
import com.epam.theatre.domain.Ticket;
import com.epam.theatre.soap.event.Delete;
import com.epam.theatre.soap.event.GetAll;
import com.epam.theatre.soap.event.GetAllForDateRange;
import com.epam.theatre.soap.event.GetAllForDateRangeResponse;
import com.epam.theatre.soap.event.GetAllNextEvents;
import com.epam.theatre.soap.event.GetAllNextEventsResponse;
import com.epam.theatre.soap.event.GetAllResponse;
import com.epam.theatre.soap.event.GetBookedTickets;
import com.epam.theatre.soap.event.GetBookedTicketsResponse;
import com.epam.theatre.soap.event.GetById;
import com.epam.theatre.soap.event.GetByIdResponse;
import com.epam.theatre.soap.event.GetByName;
import com.epam.theatre.soap.event.GetByNameResponse;
import com.epam.theatre.soap.event.Save;
import com.epam.theatre.soap.event.SaveAll;
import com.epam.theatre.soap.event.Update;

@Component
public class EventServiceClient {

	private static final Logger LOGGER = LogManager.getLogger(CustomerServiceClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void save(Event event) {
        Save save = new Save();
        save.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(save);
    }

    public void delete(Event event) {
        Delete delete = new Delete();
        delete.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(delete);
    }

    public Event getById(Long id) {
        GetById getById = new GetById();
        getById.setArg0(id);
        GetByIdResponse getByIdResponse = (GetByIdResponse) webServiceTemplate.marshalSendAndReceive(getById);
        Event event = getByIdResponse.getReturn();
        LOGGER.info("SOAP result: " + event.toString());
        return event;
    }

    public Event getByName() {
        GetByName getByName = new GetByName();
        GetByNameResponse getByNameResponse = (GetByNameResponse) webServiceTemplate.marshalSendAndReceive(getByName);
        Event event = getByNameResponse.getReturn();
        return event;
    }

    public List<Event> getAll() {
        GetAll getAll = new GetAll();
        GetAllResponse getAllResponse = (GetAllResponse) webServiceTemplate.marshalSendAndReceive(getAll);
        List<Event> events = getAllResponse.getReturn();
        return events;
    }

    public List<Event> getAllForDateRange(LocalDate from, LocalDate to) {
        GetAllForDateRange getAllForDateRange = new GetAllForDateRange();
        getAllForDateRange.setArg0(from);
        getAllForDateRange.setArg1(to);
        GetAllForDateRangeResponse getAllForDateRangeResponse = (GetAllForDateRangeResponse) webServiceTemplate
                .marshalSendAndReceive(getAllForDateRange);
        List<Event> events = getAllForDateRangeResponse.getReturn();
        return events;
    }

    public List<Event> getAllNextEvents(LocalDate to) {
        GetAllNextEvents getAllNextEvents = new GetAllNextEvents();
        getAllNextEvents.setArg0(to);
        GetAllNextEventsResponse getAllNextEventsResponse = (GetAllNextEventsResponse) webServiceTemplate
                .marshalSendAndReceive(getAllNextEvents);
        List<Event> events = getAllNextEventsResponse.getReturn();
        return events;
    }

    public void update(Event event) {
        Update update = new Update();
        update.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(update);
    }

    public void saveAll(List<Event> events) {
        SaveAll saveAll = new SaveAll();
        saveAll.setArg0(events);
        webServiceTemplate.marshalSendAndReceive(saveAll);
    }

    public List<Ticket> getBookedTickets(Event event) {
        GetBookedTickets getBookedTickets = new GetBookedTickets();
        getBookedTickets.setArg0(event);
        GetBookedTicketsResponse getBookedTicketsResponse = (GetBookedTicketsResponse) webServiceTemplate
                .marshalSendAndReceive(getBookedTickets);
        List<Ticket> tickets = getBookedTicketsResponse.getReturn();
        return tickets;
    }
}
