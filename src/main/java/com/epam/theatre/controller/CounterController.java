package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.CounterService;

@Controller
@RequestMapping("/")
public class CounterController {

	@Autowired
	private CounterService counterService;

	@RequestMapping(value = { "/counter" }, method = RequestMethod.GET)
	public ModelAndView counterAllPage(ModelAndView model) {
		model.addObject("counterAll", counterService.getAll()).setViewName("counter");
		return model;
	}

	@RequestMapping(value = { "/counter-pdf" }, method = RequestMethod.POST, produces = "application/pdf")
	public ModelAndView counterAllPdfPage(ModelAndView model) {
		return new ModelAndView("pdfView", "items", counterService.getAll());

	}

}