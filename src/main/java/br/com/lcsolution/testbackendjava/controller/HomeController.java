package br.com.lcsolution.testbackendjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("home")
	public ModelAndView view() {
		return new ModelAndView("home");
	}
}
