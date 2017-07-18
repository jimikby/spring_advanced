package com.epam.theatre.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
 
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        ModelAndView model = new ModelAndView();
        String message = e.getMessage();
        model.addObject("message", message);
        model.setViewName("error");
        return model;
    }
}
