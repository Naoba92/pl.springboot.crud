package pl.springboot.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.services.UserService;

@Controller
@AllArgsConstructor
public class MainController {

	private UserService userService;


	@GetMapping("/")
	public String login() {
		return "index";
	}

	@PostMapping("/new")
	public String start(@ModelAttribute("userForm") @Valid UserDTO userForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			userForm.setPassword(null);
			userForm.setConfirmPassword(null);
			model.addAttribute("userForm", userForm);
		} else {
			
			model.addAttribute("userForm", userForm);
			userService.save(userForm);

		}
		return "redirect:/";
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
