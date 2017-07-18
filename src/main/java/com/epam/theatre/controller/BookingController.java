package com.epam.theatre.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.domain.Ticket;
import com.epam.theatre.service.BookingService;
import com.epam.theatre.service.CustomerService;

@Controller
@RequestMapping("/")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/booking", method = RequestMethod.POST, produces = "application/pdf")
	public @ResponseBody ModelAndView bookingPage(
			@RequestParam(value = "eventScheduleId", required = true) Long eventScheduleId,
			@RequestParam(value = "ticketNumbers", required = true) String ticketNumbers,
			@RequestParam(value = "email", required = true) String email

	) throws Exception {

		Set<Long> seats = Pattern.compile(",").splitAsStream(ticketNumbers).map(Long::parseLong)
				.collect(Collectors.toSet());
		seats = bookingService.checkSeats(seats, eventScheduleId);
		Long customerId = customerService.takeByEmail(email).getCustomerId();
		
		Set<Ticket> tickets = bookingService.takeTicketsWithPrices(eventScheduleId, seats, customerId);
		bookingService.bookTickets(tickets, customerId);
		List<Ticket> list = new ArrayList<Ticket>(tickets);
		return new ModelAndView("pdfView", "items", list);
	}

}