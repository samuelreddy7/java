package com.bcj.helpdesk.controller.registrationandlogin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcj.helpdesk.model.registerandlogin.Login;
import com.bcj.helpdesk.model.registerandlogin.User;
import com.bcj.helpdesk.service.registerandlogin.RegistrationAndLoginService;

@Controller
public class HomeController {

	@Autowired
	private RegistrationAndLoginService rlService;
	

	public void setRlservice(RegistrationAndLoginService rlService) {
		this.rlService = rlService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<String> userType = rlService.retrieveUserType();
		List<String> usStates = rlService.retrieveStates();
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("usStates", usStates);
		mav.addObject("userType", userType);
		return mav;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(User user, Model model) {

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Login login, Model model, HttpSession session) {
		ModelAndView mav = null;
		String userType = rlService.verifyUser(login);
		if ("student".equals(userType)||"consultant".equals(userType)) {
			session.setAttribute("loginId", login.getLoginId());
			session.setAttribute("username", login.getUsername());
			session.setAttribute("userType", userType);
			String user = (String) session.getAttribute("username");
			System.out.println(user+"at login");
			System.out.println("session iD : "+ session.getId());
			System.out.println("Session loginId at controller: "+ session.getAttribute("loginId"));
			mav = new ModelAndView("login");
			mav.addObject("message", "welcome "+login.getUsername());

			return mav;
		}else if("employee".equals(rlService.verifyUser(login))){
			session.setAttribute("loginId", login.getLoginId());
			session.setAttribute("username", login.getUsername());
			session.setAttribute("userType", userType);
			String user = (String) session.getAttribute("username");
			System.out.println("Session username at home controller"+user);
			System.out.println("Session loginId at controller: "+ session.getAttribute("loginId"));
			mav = new ModelAndView("employlogin");
			mav.addObject("message", "welcome "+login.getUsername());
			return mav;
		}		
		else{
			mav = new ModelAndView("error");
			mav.addObject("message", "Please enter valid username and password!");
			return mav;
	}


	}
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		List<String> userType = rlService.retrieveUserType();
		List<String> usStates = rlService.retrieveStates();
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("usStates", usStates);
		mav.addObject("userType", userType);
		session.invalidate();
		return mav;
	}
	}
