package com.epam.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.CustomerAccountService;

@Controller
@RequestMapping("/")
public class CustomerAccountController {

	@Autowired
	private CustomerAccountService customerAccountService;

	@RequestMapping(value = { "/customer-account" }, method = RequestMethod.GET)
	public ModelAndView customerAllPage(ModelAndView model) {
		model.addObject("customerAccounts", customerAccountService.getAll()).setViewName("customer-account");
		return model;
	}

}