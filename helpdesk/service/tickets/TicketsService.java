package com.bcj.helpdesk.service.tickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcj.helpdesk.dao.tickets.TicketsDao;
import com.bcj.helpdesk.model.tickets.Tickets;

@Service
public class TicketsService {

	@Autowired
	TicketsDao ticketsDao;

	public boolean saveTicket(Tickets tickets, int loginId, String username) {
		if (ticketsDao.saveTicket(tickets, loginId, username)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Tickets> retrieveTicketsData(int loginId) {
		List<Tickets> tickets = new ArrayList<Tickets>();

		List<Map<String, Object>> ticketsList = ticketsDao.retrieveTicketsData(loginId);
		for (Map<String, Object> map : ticketsList) {
		
			Tickets ticketsObject = new Tickets();

			ticketsObject.setTicketId((int) map.get("ticketId"));
			ticketsObject.setCategory((String) map.get("category"));
			ticketsObject.setSubcategory((String) map.get("subcategory"));
			ticketsObject.setDescription((String) map.get("description"));
			ticketsObject.setStat((String) map.get("stat"));
			ticketsObject.setComments((String) map.get("comments"));

			tickets.add(ticketsObject);
		}
		return tickets;

	}

	public List<String> retrieveCategory() {
		return ticketsDao.retrieveCategory();
	}

	public List<String> retrieveSubCategory(String category) {
		return ticketsDao.retrieveSubCategory(category);
	}

	public List<Tickets> retrieveTicketsData() {

		List<Tickets> ticketsList = ticketsDao.retrieveTicketsData();
	
		return ticketsList;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
