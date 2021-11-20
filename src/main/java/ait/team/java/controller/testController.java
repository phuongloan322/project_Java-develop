package ait.team.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 
 */
@Controller
public class testController {
	@GetMapping(value = "testDate")
	public String testDate() {
		return "testDate";
	}
	
	@GetMapping(value = "new")
	public String testNew() {
		return "Views/Main";
	}
}
