package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.TicketService;

@Controller
@RequestMapping("/")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@RequestMapping(value = { "/ticket" }, method = RequestMethod.GET)
	public ModelAndView ticketAllPage(ModelAndView model) {
		model.addObject("tickets", ticketService.getAll()).setViewName("ticket");
		return model;
	}
	
	@RequestMapping(value = { "ticket/event-schedule/{eventScheduleId}" }, method = RequestMethod.GET)
	public ModelAndView ticketBuyPage(@PathVariable(value = "eventScheduleId") Long eventScheduleId,
			ModelAndView model) {

		model.addObject("tickets", ticketService.takeTicketsByEventScheduleId(eventScheduleId))
			 .addObject("eventScheduleId", eventScheduleId)
			 .setViewName("book-ticket");
		
		return model;
	}

}