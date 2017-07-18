package com.epam.theatre.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String homePagePost(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String errorPage(ModelMap model) {
		return "error";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

			Cookie cookie = new Cookie("my-remember-me", null);
			cookie.setMaxAge(0);
			cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
			response.addCookie(cookie);

		}
		return "redirect:/login";
	}

}