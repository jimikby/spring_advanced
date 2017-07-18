package com.epam.theatre.rest.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.theatre.domain.Customer;
import com.epam.theatre.domain.Ticket;
import com.epam.theatre.service.BookingService;
import com.epam.theatre.service.CustomerService;
import com.epam.theatre.service.TicketService;
import com.google.common.collect.Lists;

@RestController
public class TicketsRestController {

	private static final Logger LOGGER = LogManager.getLogger(TicketsRestController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CustomerService userService;

    @Autowired
    private BookingService bookingService;

    @SuppressWarnings("serial")
	@ResponseBody
    @RequestMapping(value = "/tickets/book/{ticketId}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> bookTicket(HttpServletRequest request, @PathVariable("ticketId") String ticketId,
            @RequestBody Ticket ticket) throws Exception {

    	LOGGER.info("Booking Ticket ... ");

        Principal principal = request.getUserPrincipal();
        Customer customer = null;
        if (principal != null) {
            String email = principal.getName();
            customer = userService.takeByEmail(email);
        }

        Long id = Long.valueOf(ticketId);
        Ticket currentTicket  = ticketService.getById(id);

        if (currentTicket == null) {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }

        if (!ticket.getTicketId().equals(id)) {
        	LOGGER.info("ticketId: " + ticketId + " must be equal id: " + ticket.getTicketId() + " of ticket in request body");
            return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
        }

        bookingService.bookTickets((Set<Ticket>) new HashSet<Ticket>() {{add(ticket);}}, customer.getCustomerId());

        return new ResponseEntity<Ticket>(ticket , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getAllTicketsPdf() {
    	LOGGER.info("Getting All Tickets ...");
        List<Ticket> tickets = ticketService.getAll();
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getById(Long.parseLong(ticketId));
        if (ticket == null) {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getTicketByIdPdf(@PathVariable String ticketId) {
    	LOGGER.info("Getting Ticket with id: " + ticketId + " ...");
        Ticket ticket = ticketService.getById(Long.parseLong(ticketId));
        if (ticket == null) {
            return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Ticket>>(Lists.newArrayList(ticket), HttpStatus.OK);
    }
}
