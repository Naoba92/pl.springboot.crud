package pl.springboot.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

//	@GetMapping("/")
//		public String login(){
//			return "index";
//		}
	
	
	@PostMapping("/")
	public String login(Model model) {
		model.addAttribute("message", "hi everyone!!!");
		return "index";
	}

	@GetMapping("/")
	public String start(Model model) {
		model.addAttribute("message", "hi everyone!!!");
		return "index";
	}
	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/?logout";
	}
}
