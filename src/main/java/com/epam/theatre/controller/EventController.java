package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.EventService;

@Controller
@RequestMapping("/")
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping(value = { "/event-pdf" }, method = RequestMethod.POST, produces = "application/pdf")
	public ModelAndView counterAllPdfPage(ModelAndView model) {
		return new ModelAndView("pdfView", "items", eventService.getAll());

	}

	@RequestMapping(value = { "/event" }, method = RequestMethod.GET)
	public ModelAndView eventAllPage(ModelAndView model) {
		model.addObject("eventAll", eventService.getAll()).setViewName("event");
		return model;
	}

}