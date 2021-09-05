package pl.springboot.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@AllArgsConstructor
public class LoginController {

	private MessageSource messages;
	
	@GetMapping("/")
	public String login(Model model, @Param ("errorLogin") boolean errorLogin, WebRequest request) {
		String message = messages.getMessage("message.badCredentials", null, request.getLocale());
		if(errorLogin){
			model.addAttribute("message", message);
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/?logout";
	}
}
