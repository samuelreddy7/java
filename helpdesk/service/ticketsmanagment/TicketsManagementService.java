package com.bcj.helpdesk.service.ticketsmanagment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcj.helpdesk.dao.ticketsmanagement.TicketsManagementDao;
import com.bcj.helpdesk.model.tickets.Tickets;

@Service
public class TicketsManagementService {

	@Autowired
	TicketsManagementDao ticketsMngDao;
	
	public List<Tickets> retrieveApprovedTickets() {
		
		
		List<Tickets> tickets = ticketsMngDao.retrieveTicketsData();
		
		List<Tickets> approvedTickets = new ArrayList<Tickets>();
			for(Tickets approved : tickets){
				if("approved".equals(approved.getStat())){
					approvedTickets.add(approved);
				}
			}
		return approvedTickets;
	}
	
	public List<Tickets> retrievePendingTickets() {
		
		
		List<Tickets> tickets = ticketsMngDao.retrieveTicketsData();
		
		List<Tickets> pendingTickets = new ArrayList<Tickets>();
			for(Tickets pending : tickets){
				if("pending".equals(pending.getStat())){
					pendingTickets.add(pending);
				}
			}
		
		return pendingTickets;
	}

	public List<Tickets> retrieveRejectedTickets() {
		
		
		List<Tickets> tickets = ticketsMngDao.retrieveTicketsData();
		
		List<Tickets> rejectedTickets = new ArrayList<Tickets>();
			for(Tickets rejected : tickets){
				if("rejected".equals(rejected.getStat())){
					rejectedTickets.add(rejected);
				}
			}
		
		return rejectedTickets;
	}
}
