package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.CustomerService;

@Controller
@RequestMapping("/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = { "/customer-pdf" }, method = RequestMethod.POST, produces = "application/pdf")
	public ModelAndView counterAllPdfPage(ModelAndView model) {
		return new ModelAndView("pdfView", "items", customerService.getAll());

	}

	@RequestMapping(value = { "/customer" }, method = RequestMethod.GET)
	public ModelAndView customerAllPage(ModelAndView model) {
		model.addObject("customerAll", customerService.getAll()).setViewName("customer");
		return model;
	}

	@RequestMapping(value = { "/customer/delete/{Id}" }, method = RequestMethod.GET)
	public ModelAndView customerDeletePage(ModelAndView model) {
		model.setViewName("customer-delete");
		return model;
	}

	@RequestMapping(value = { "/customer/edit/{customerId}" }, method = RequestMethod.GET)
	public ModelAndView customerEditPage(@PathVariable(value = "customerId") Long customerId,
			ModelAndView model) {

		model.addObject("customer", customerService.getById(customerId)).setViewName("customer");
		model.setViewName("customer-edit");
		return model;
	}
	
	@RequestMapping(value = { "/customer/save" }, method = RequestMethod.GET)
	public ModelAndView customerSave(ModelAndView model) {
		model.addObject("customerAll", customerService.getAll()).setViewName("customer");
		return model;
	}

}