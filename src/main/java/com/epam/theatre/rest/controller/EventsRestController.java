package com.epam.theatre.rest.controller;

import java.util.List;

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

import com.epam.theatre.domain.Event;
import com.epam.theatre.domain.Ticket;
import com.epam.theatre.service.EventService;
import com.epam.theatre.service.TicketService;

@RestController
public class EventsRestController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private TicketService ticketService;
    

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Event>> getAll() {
        List<Event> events = eventService.getAll();
        if(events.isEmpty()){
            return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Event> getById(@PathVariable String eventId) {
        Event event = eventService.getById(Long.parseLong(eventId));
        if (event == null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getBookedTickets(@PathVariable String eventId) {
        Event event = eventService.getById(Long.parseLong(eventId));
        List<Ticket> tickets = ticketService.takeTicketsByEventId(event.getEventId());
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody Event event, UriComponentsBuilder ucBuilder) {

        if (eventService.getById(event.getEventId()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        eventService.save(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/events/{id}").buildAndExpand(event.getEventId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Event> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {

        Event currentEvent = eventService.getById(Long.valueOf(id));

        if (currentEvent==null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        currentEvent.setEventId(event.getEventId());
        currentEvent.setEventName(event.getEventName());
        currentEvent.setRating(event.getRating());
        currentEvent.setBasePrice(event.getBasePrice());


        eventService.update(currentEvent.getEventId(),currentEvent);
        return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id) {

        Event event = eventService.getById(Long.valueOf(id));
        if (event == null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        eventService.remove(event.getEventId());
        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
    }
}
