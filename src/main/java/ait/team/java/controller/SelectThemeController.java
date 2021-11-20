package ait.team.java.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ait.team.java.controller.input.InputSelectTheme;
import ait.team.java.controller.output.SelectThemeData;
import ait.team.java.dto.output.DataSearchTheme;
import ait.team.java.service.impl.SelectThemeService;

@Controller
public class SelectThemeController {

	@Autowired
	SelectThemeService selectThemeService;
	
	@GetMapping(value = "SelectTheme")
	public @ResponseBody SelectThemeData  selectTheme(Model model, HttpSession session) {
		
		String userNo = (String) session.getAttribute("userNo");
		SelectThemeData selectThemeData = selectThemeService.getSelectThemeData(userNo);
		return selectThemeData;
	}
	
	@PostMapping(value = "SelectTheme/Search")
	public @ResponseBody DataSearchTheme  searchTheme(@RequestBody InputSelectTheme inputSearchTheme, HttpSession session) {
		String userNo = (String) session.getAttribute("userNo");
		DataSearchTheme dataSearchTheme = selectThemeService.searchTheme(inputSearchTheme, userNo);
		return dataSearchTheme;
	}
}
