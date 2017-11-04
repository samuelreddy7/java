package com.bcj.helpdesk.controller.tickets;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcj.helpdesk.model.tickets.Tickets;
import com.bcj.helpdesk.service.tickets.TicketsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller

public class TicketsController {

	@Autowired
	private TicketsService ticketsService;
	
	public void setTicketService(TicketsService ticketsService){
		this.ticketsService = ticketsService;
	}
	
	@RequestMapping(value = "/createTicket", method = RequestMethod.POST)
	public ModelAndView createTicket(Tickets tickets,Model model, HttpSession session){
		
		System.out.println("session iD : "+ session.getId());
		System.out.println("session loginID at tickets COntroller: "+session.getAttribute("loginId"));
		String username = (String)session.getAttribute("username");
		
		int loginId = (int) session.getAttribute("loginId");
		
		System.out.println("login id at createTicket : "+ loginId);
		
		ModelAndView mav =null;

		if(ticketsService.saveTicket(tickets,loginId,username)){
		
		mav = new ModelAndView("login");
			return mav;
		}else{
			mav = new ModelAndView("login");
			return mav;
		}
		
	}
	
	@RequestMapping(value = "/displayCreateTickets")
	public ModelAndView displayCreateTicket(HttpSession session){
		System.out.println((String)session.getId());
		ModelAndView mav = new ModelAndView("tickets");
		mav.addObject("ticketId", TicketsService.randomAlphaNumeric(6));
		mav.addObject("loginId", session.getAttribute("loginId"));
		mav.addObject("category", ticketsService.retrieveCategory());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/displayTickets")
	public String myTickets(HttpSession session){
		System.out.println("at displayTickets - my tickets");
		System.out.println(session.getId());
		int loginId = (int)session.getAttribute("loginId");
		System.out.println(loginId + "at mytickets- displaytickets");
		List<Tickets> myTickets = ticketsService.retrieveTicketsData(loginId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(myTickets);
		return jsonArray;

	}
	
	@ResponseBody
	@RequestMapping(value = "/loadSubCat", method =RequestMethod.POST)
	public String loadSubCat(@RequestBody String category){
		
		List<String> subCategory = ticketsService.retrieveSubCategory(category);
		
		String subCategoryHtmlStr = "";
		for(String subC : subCategory){
			subCategoryHtmlStr+="<option value = '"+subC+"'>" +subC+"</option>";
			
		}
		return subCategoryHtmlStr;
		
		
	}
	
	@RequestMapping(value = "/studentTickets")
	public String studentTickets(){
		System.out.println("harsha");
		return "studentTickets";
		
	}
	
	@RequestMapping("/returntologin")
	public ModelAndView returnToLogin(HttpSession session){
		String message = (String) session.getAttribute("username");
		String userType = (String)session.getAttribute("userType");
		System.out.println(message +" at ticket controller");
		ModelAndView mav = null;
		if("employee".equals(userType)){
				mav = new ModelAndView("employlogin");
				mav.addObject("message", message);
				return mav;
		}else{
			mav = new ModelAndView("login");
			return mav;
		}
	}
	

	
}
