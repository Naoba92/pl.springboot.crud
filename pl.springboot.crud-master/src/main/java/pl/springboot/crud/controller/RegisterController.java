package pl.springboot.crud.controller;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.error.handling.RegisterErrorHandling;
import pl.springboot.crud.event.OnRegistrationCompleteEvent;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.model.User;
import pl.springboot.crud.model.VerificationToken;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.services.UserService;

@Controller
@AllArgsConstructor
public class RegisterController {
	
	private UserService userService;
	private RegisterErrorHandling errorHandler;
	private ApplicationEventPublisher eventPublisher;
    private MessageSource messages;


	@PostMapping("/new")
	public String registerUserAccount(@ModelAttribute("userForm") @Valid UserDTO userForm, Errors error,Model model, WebRequest request) throws UserAlreadyExistException {
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
				User registered = userService.registerNewUser(userForm, request);
				String appUrl = request.getContextPath();
		        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
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

	
	
	@GetMapping("/registrationConfirm")
	public String confirmRegistration
	  (WebRequest request, Model model, @RequestParam("token") String token) {
	 
	    Locale locale = request.getLocale();
	    
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	    
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
	        model.addAttribute("message", messageValue);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	    user.setEnabled(true); 
	    userService.saveRegisteredUser(user); 
	    return "index"; 
	}
	
	@GetMapping("/registerError")
	public String registerError(Model model) {
		model.addAttribute("message", errorHandler.getErrorDesription());
		return "/index";
	}

	@GetMapping("/registerSuccess")
	public String registerSuccess(Model model, WebRequest request) {
		String message = messages.getMessage("message.regSucc", null,"Rejestracja przebiegła pomyśle. Prosimy potwierdzić adres email.", request.getLocale());
		model.addAttribute("regSucc",message);
		return "index";
	}
}
