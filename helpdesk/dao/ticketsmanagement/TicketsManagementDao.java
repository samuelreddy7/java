package com.bcj.helpdesk.dao.ticketsmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcj.helpdesk.model.tickets.Tickets;

@Repository
public class TicketsManagementDao {
@Autowired
JdbcTemplate jdbcTemplate;
public List<Tickets> retrieveTicketsData() {
	
	String query = "select * from tickets";
	
	List<Tickets> tickets = jdbcTemplate.query(query, new BeanPropertyRowMapper<Tickets>(Tickets.class));
	return tickets;
}
public void approveTickets(String stat, int ticketId, String comments) {
	String query = "update tickets set stat=?, comments=? where ticketId=?";
	jdbcTemplate.update(query, new Object[]{stat, comments, ticketId});
}

public void rejectTickets(String stat, int ticketId, String comments) {
	String query = "update tickets set stat=?, comments=? where ticketId=?";
	jdbcTemplate.update(query, new Object[]{stat, comments, ticketId});
}

}
