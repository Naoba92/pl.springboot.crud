package pl.springboot.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.error.handling.RegisterErrorHandling;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.services.UserService;

@Controller
@AllArgsConstructor
public class MainController {
	@Autowired
	private UserService userService;
	private RegisterErrorHandling errorHandler;

	@GetMapping("/")
	public String login() {
		return "index";
	}

	@GetMapping("/registerError")
	public String registerError(Model model) {
		model.addAttribute("message", errorHandler.getErrorDesription());
		return "/index";
	}

	@GetMapping("/registerSuccess")
	public String registerSuccess(Model model) {
		model.addAttribute("messageSuccess",
				"Rejestracja przebiegła pomyśle. Prosimy potwierdzić adres email.");
		return "index";
	}

	@PostMapping("/new")
	public String registerUserAccount(@ModelAttribute("userForm") @Valid UserDTO userForm, Errors error,Model model) throws UserAlreadyExistException {
		boolean userExist = false;
		if (error.hasErrors()) {
			if (error.hasGlobalErrors()) { 
				errorHandler.saveError(error.getGlobalError().getDefaultMessage());
			} else {
				errorHandler.saveError(error.getFieldError().getDefaultMessage());
			}
			return "redirect:/registerError";
		} else {
			try {
				userService.registerNewUser(userForm);
			} catch (UserAlreadyExistException uaeEx) {
				System.out.println("UserExists");
				userExist = true;
				errorHandler.saveError(uaeEx.getMessage());
			}
		}
		if (userExist) {
			return "redirect:/registerError";
		}
		return "redirect:/registerSuccess";
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
