package pl.springboot.crud.controller;

import lombok.AllArgsConstructor;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.springboot.crud.model.User;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.services.UserService;

@Controller
@AllArgsConstructor
public class UserController {
	
	private UserRepository userRepository;
	private UserService userService;

	@PostMapping("/")
	@Secured("user_role")
	public String start(@ModelAttribute("loginForm") User loginForm,
			Model model) {
		model.addAttribute("role", userRepository.findByUsernameFetchRoles(loginForm.getUsername()).getAuthorities());
		return "redirect:/";
	}
}
