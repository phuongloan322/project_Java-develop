package ait.team.java.controller;

import java.util.*;  
import java.io.*; 

import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ait.team.java.common.ActiveDirectory;
import ait.team.java.dto.UserDto;
import ait.team.java.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	IUserService userService;
	
	private final String PROPERTIES_FILE = "src/main/resources/application.properties";
	private final String ERROR = "ユーザNo、またはパスワードが無効です";
	
	//load page login
	@GetMapping(value = "login")
	public String getLogin(@RequestParam(value = "error", required = false ) String error, Model model) {
		if(error!=null) {
			model.addAttribute("error", error);
		}
		return "views/login";
	}
	
	//load page login
	@GetMapping(value = "/")
	public String init() {
		return "views/login";
	}
	
	//post form login
	@PostMapping(value = "loginPost")
	public RedirectView postLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request, Model model,
			HttpSession session, RedirectAttributes redirectAttributes){
		
		String userNameRoot = "";
		String passwordRoot = "";
		String donmain = "";
		// load file properties
		try {
			FileReader reader = new FileReader(PROPERTIES_FILE);
			Properties properties = new Properties();
			properties.load(reader);
			userNameRoot = properties.getProperty("ldap.username");
			passwordRoot = properties.getProperty("ldap.password");
			donmain = properties.getProperty("ldap.donmain");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// auto login
		if (type.equals("auto")) {
			username = System.getProperty("user.name").toUpperCase();
			try {
				ActiveDirectory active = new ActiveDirectory(userNameRoot,
						passwordRoot, donmain);
				NamingEnumeration<SearchResult> searchname = active.searchUser(username, "username",
						donmain);
				if (searchname.hasMoreElements()) {
					// get user database
					UserDto auth = userService.findByUserNo(username);

					//if success
					if (auth != null) {
						//set session authentication
						session.setAttribute("userNo", auth.getUserNo());
						session.setAttribute("roleCode", auth.getRoleCode());
						session.setAttribute("siteCode", auth.getSiteCode());
						return new RedirectView("menu");
					}
				}
				redirectAttributes.addAttribute("error", ERROR);
				return new RedirectView("login");
			} catch (Exception e) {
				redirectAttributes.addAttribute("error", ERROR);
				return new RedirectView("login");
			}
		} else {// login by userName and password
			try {
				ActiveDirectory active = new ActiveDirectory(username, password,
						donmain);
				NamingEnumeration<SearchResult> searchname = active.searchUser(username, "username",
						donmain);
				// get user database
				UserDto auth = userService.findByUserNo(username);

				if (searchname.hasMoreElements()) {
					// redirect menu if success
					if (auth != null) {
						// set session Authentication
						session.setAttribute("userNo", auth.getUserNo());
						session.setAttribute("roleCode", auth.getRoleCode());
						session.setAttribute("siteCode", auth.getSiteCode());
						return new RedirectView("menu");
					}
				}
				redirectAttributes.addAttribute("error", ERROR);
				return new RedirectView("login");
			} catch (Exception e) {
				redirectAttributes.addAttribute("error", ERROR);
				return new RedirectView("login");
			}
		}
	}
}
