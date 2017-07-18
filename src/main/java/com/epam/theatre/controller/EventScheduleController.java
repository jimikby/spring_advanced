package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.EventScheduleService;

@Controller
@RequestMapping("/")
public class EventScheduleController {

	@Autowired
	private EventScheduleService eventScheduleService;
	
	
	@RequestMapping(value = { "/event-schedule-pdf" }, method = RequestMethod.POST, produces = "application/pdf")
	public ModelAndView counterAllPdfPage(ModelAndView model) {
		return new ModelAndView("pdfView", "items", eventScheduleService.getAll());

	}

	@RequestMapping(value = { "/event-schedule" }, method = RequestMethod.GET)
	public ModelAndView eventAllPage(ModelAndView model) {
		model.addObject("eventScheduleAll", eventScheduleService.getAll()).setViewName("event-schedule");
		return model;
	}
	
	@RequestMapping(value = { "/event-schedule/event/{eventId}" }, method = RequestMethod.GET)
	public ModelAndView eventSchedulePage(@PathVariable(value = "eventId") Long eventId,
			ModelAndView model) {

		model.addObject("eventScheduleAll", eventScheduleService.takeByEventId(eventId)).setViewName("event-schedule");
		return model;
	}
}