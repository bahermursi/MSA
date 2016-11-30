package com.listerdigital.MSA.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.listerdigital.MSA.domain.User;

@Controller
@SessionAttributes("name")
public class HomeController {
@RequestMapping(value="/")
public String welcome(Model model) {
	model.addAttribute("greeting", "Getting Started");
	User user = new User();
    model.addAttribute("user", user);
    model.addAttribute("name","MSA");
	return "home";
}
}