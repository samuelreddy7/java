package com.bcj.helpdesk.controller.ticketsmanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcj.helpdesk.dao.ticketsmanagement.TicketsManagementDao;
import com.bcj.helpdesk.model.tickets.Tickets;
import com.bcj.helpdesk.service.ticketsmanagment.TicketsManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class TicketsManagementController {
	
	@Autowired
TicketsManagementService ticketsMngService;
	
	@Autowired
	TicketsManagementDao ticketsMngDao;
	
	@RequestMapping("/employTickets")
	public String displayEmployView(){
		return "employTickets";
	}
	
	@ResponseBody
	@RequestMapping("/approvedTickets")
	public String approvedTickets(){
		List<Tickets> approvedTickets = ticketsMngService.retrieveApprovedTickets();
		for(Tickets t: approvedTickets){
		System.out.println(t + "at approvedTickets ticketsMngController");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(approvedTickets);
		return jsonArray;
	}
	
	@ResponseBody
	@RequestMapping("/pendingTickets")
	public String pendingTickets(){
		List<Tickets> pendingTickets = ticketsMngService.retrievePendingTickets();
		for(Tickets t: pendingTickets){
		System.out.println(t + "at pendingTickets ticketsMngController");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(pendingTickets);
		return jsonArray;
	}
	
	@ResponseBody
	@RequestMapping("/rejectedTickets")
	public String rejectedTickets(){
		List<Tickets> rejectedTickets = ticketsMngService.retrieveRejectedTickets();
		for(Tickets t: rejectedTickets){
		System.out.println(t + "at rejectedTickets ticketsMngController");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(rejectedTickets);
		return jsonArray;
	}
	
	@ResponseBody
	@RequestMapping("/approve")
	public void approveTickets(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("val") int ticketId, @RequestParam("comments") String comments){
		System.out.println("at approve");
		
		ticketsMngDao.approveTickets("approved", ticketId, comments);
	}
	
	@ResponseBody
	@RequestMapping("/reject")
	public void rejectTickets(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("val") int ticketId, @RequestParam("comments") String comments){
		ticketsMngDao.rejectTickets("rejected", ticketId, comments);
	}
}
