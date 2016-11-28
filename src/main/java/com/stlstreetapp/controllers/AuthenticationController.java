package com.stlstreetapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stlstreetapp.models.User;
import com.stlstreetapp.models.Zone;
import com.stlstreetapp.models.dao.UserDao;
import com.stlstreetsapp.services.NotificationService;

@Controller
public class AuthenticationController extends AbstractController{

	@Autowired
	private UserDao UserDao;
	
	@Autowired 
	 private HttpSession httpSession;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userDao.findByEmail(email);
		String noUser;
		String invalid;
		if(user == null){
			noUser = "User name not found";
			model.addAttribute("noUser", noUser);
			return "/login";
		}
		
		if(!user.isMatchingPassword(password)){
			invalid = "Invalid password";
			model.addAttribute("invalid", invalid);
			return "/login";
		}
		setUserInSession(httpSession, user);
		return "redirect:/myaccount";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model){
        request.getSession().invalidate();
		String logout = "Successfully logged out";
		model.addAttribute("logout", logout);
		return "redirect:/myaccount";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		String number = request.getParameter("number");
		String carrier = request.getParameter("carrier");
		String newNumber = number.replaceAll("[^0-9]","");
		
		if(email == null || password == null || verify == null || carrier == null || number == null){
			String error = "Please complete all fields.";
			model.addAttribute("emptyError", error);
			return "signup";
		}
		if(UserDao.findByEmail(email) != null){
			String error = "This email address is already in use.";
			model.addAttribute("existingError", error);
			return "signup";
		}
		User newUser = new User(email, password, number, carrier);
		if(!newUser.isValidEmail(email)){
			String error = "Invalid email address";
			model.addAttribute("emailError", error);
			return "signup";
		}
		
		if(!newUser.isValidPassword(password)){
			String error = "Invalid password";
			model.addAttribute("passwordError", error);
			return "signup";
		}
		if(!password.equals(verify)){
			String error = "Passwords do not match";
			model.addAttribute("verifyError", error);
			return "signup";
		}
		if(!newUser.checkNumber(newNumber)){
			String error = "Error: Phone number can not begin with a 0 or 1.";
			model.addAttribute("numberError", error);
			return "signup";
		}
		if(!newUser.checkNumberLength(newNumber)){
			String error = "Error: Phone number must be 10 digits long and contain digits (0-9) only.";
			model.addAttribute("numberError", error);
			return "signup";
		}
		
		UserDao.save(newUser);
		setUserInSession(httpSession, newUser);
		return "redirect:newzone";
	}	
	@RequestMapping(value = "/newzone", method = RequestMethod.GET)
	public String newZoneForm(HttpServletRequest request, Model model){
		
		return "newzone";
	}
	@RequestMapping(value = "/newzone", method = RequestMethod.POST)
	public String zoneConfirm(HttpServletRequest request, Model model){
		String znumber = request.getParameter("znumber");
		
		User user = getUserFromSession(httpSession);
		Zone newZone = new Zone(user, znumber);
		if(!newZone.checkZNumber(znumber)){
			String error= "Please select a number between 1 and 40 and can not contain characters other than numbers.";
			model.addAttribute("error", error);
			return "newzone";
		}
		zoneDao.save(newZone);
		NotificationService mail = new NotificationService(javaMailSender);
		
		mail.confirmEmail(user);
		
		return "confirm";
	}
	
	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String showAccount(HttpServletRequest request, Model model){
		User user = getUserFromSession(httpSession);
		
		List<Zone>zones = zoneDao.findByEmail(user);
		model.addAttribute("email", user.getEmail());
		model.addAttribute("number", user.getNumber());
		model.addAttribute("carrier", user.getCarrier());
		model.addAttribute("zones", zones);
		return "myaccount";
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(HttpServletRequest request, Model model){
		return "edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, Model model){
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		String number = request.getParameter("number");
		String carrier = request.getParameter("carrier");
		String newNumber = number.replaceAll("[^0-9]","");
		User user = getUserFromSession(httpSession);
		
		
		if(!user.isValidPassword(password)){
			String error = "Invalid password";
			model.addAttribute("passwordError", error);
			return "edit";
		}
		if(!password.equals(verify)){
			String error = "Passwords do not match";
			model.addAttribute("verifyError", error);
			return "edit";
		}
		if(!user.checkNumber(newNumber)){
			String error = "Error: Phone number can not begin with a 0 or 1.";
			model.addAttribute("numberError", error);
			return "edit";
		}
		if(!user.checkNumberLength(newNumber)){
			String error = "Error: Phone number must be 10 digits long and contain digits (0-9) only.";
			model.addAttribute("numberError", error);
			return "edit";
		}
		
		user.setPwHash(password);
		user.setNumber(newNumber);
		user.setCarrier(carrier);
		userDao.save(user);
		return "redirect:/myaccount";
	}
	//@Scheduled(cron = "*/2 * * * * *")
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancelAccount(){
		return "cancel";
	}
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String cancelConfirm(HttpServletRequest request, Model model){
		User user = getUserFromSession(httpSession);
		userDao.delete(user);
		String confirm = "Your account has been deleted and you will no longer receive notifications.";
		model.addAttribute("confirm", confirm);
		return "index";
	}
	
		
}
