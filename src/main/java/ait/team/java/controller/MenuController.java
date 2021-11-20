package ait.team.java.controller;

import java.text.ParseException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ait.team.java.controller.output.MenuOutput;
import ait.team.java.service.ICalendarService;

@Controller
public class MenuController {
	@Autowired
	ICalendarService calendarService;
	
	//load page menu
	@GetMapping(value = "/menu")
	public String getMenu(Model model, HttpSession session){
		
		String userNo = (String) session.getAttribute("userNo");
		String siteCode = (String) session.getAttribute("siteCode");
		MenuOutput menuOutput = null;
		try {
			menuOutput = calendarService.getMenu(userNo, siteCode);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("menuOutput", menuOutput);
		return "views/menu";
	}
}
