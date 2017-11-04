package com.bcj.helpdesk.dao.tickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcj.helpdesk.model.tickets.Tickets;

@Repository
public class TicketsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean saveTicket(Tickets tickets, int loginId, String username){
		
		String query = "insert into tickets (category, subcategory, description, stat, comments, login_loginId, username, ticketNumber) values (?,?,?,?,?,?,?,?)";
		System.out.println("The loginId is :"+loginId);
		System.out.println("loginid from login class is : "+ loginId);
		
		if(0 != jdbcTemplate.update(query, new Object[] { tickets.getCategory(), tickets.getSubcategory(), tickets.getDescription(), 
				 "pending", "yet to resolve..", loginId, username, tickets.getTicketNumber() })){
			
			return true;
		}else{
			return false;
		}
		
	}
	
	public List<Map<String, Object>> retrieveTicketsData(int loginId){
		String query = "select ticketId, category, subcategory, description, stat, comments from tickets where login_loginId=?";
		
		List<Map<String, Object>> tickets = jdbcTemplate.queryForList(query,new Object[]{loginId});
		
		return tickets;
	}
	
	public List<String> retrieveCategory(){
		String query = "select categories from helpdesk_categories";
		
		List<Map<String, Object>> categoryDb = jdbcTemplate.queryForList(query);
		
		List<String> category = new ArrayList<String>();
		
		for (Map<String, Object> map : categoryDb) {
		    for (Map.Entry<String, Object> entry : map.entrySet()) {
		    	System.out.println(entry.getKey() + " - " + entry.getValue());
		    	category.add((String) entry.getValue());
		    }
		}
		return category;
	}
	public List<String> retrieveSubCategory(String category){
		
		
		String query1 = "select helpdesk_categoriesId from helpdesk_categories where categories=?";
		int categoryId = jdbcTemplate.queryForObject(query1, new Object[]{category}, Integer.class);
		
		String query = "select subcategories from helpdesk_subcategories where categoryId=?";
		List<Map<String, Object>> subcategoryDb = jdbcTemplate.queryForList(query, new Object[] {categoryId});
		List<String> subcategory = new ArrayList<String>();
		
		for (Map<String, Object> map : subcategoryDb) {
		    for (Map.Entry<String, Object> entry : map.entrySet()) {
		    	System.out.println(entry.getKey() + " - " + entry.getValue());
		    	subcategory.add((String) entry.getValue());
		    }
		}
		return subcategory;
		
		
	}

	public List<Tickets> retrieveTicketsData() {
		
		String query = "select * from tickets";
		
		List<Tickets> tickets = jdbcTemplate.query(query, new BeanPropertyRowMapper<Tickets>(Tickets.class));
		return tickets;
	}
}
