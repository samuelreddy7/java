package com.bcj.helpdesk.dao.registerandlogin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcj.helpdesk.model.registerandlogin.Login;
import com.bcj.helpdesk.model.registerandlogin.UsStates;
import com.bcj.helpdesk.model.registerandlogin.User;
import com.bcj.helpdesk.model.registerandlogin.UserType;
import com.mysql.jdbc.Connection;
@Repository
public class UserDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JdbcOperations jdbcOperations;
	Connection connection = null;

	public String saveUser(User user) {

		String query = "insert into user (firstName, lastName, email, phone, userType) values (?,?,?,?,?)";

		if (0 != jdbcOperations.update(query, new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPhone(), user.getUserType() })) {
			String query4 = "select userType from user where firstName=?&&lastName=?";
			String userType = jdbcTemplate.queryForObject(query4,
					new Object[] { user.getFirstName(), user.getLastName() }, String.class);

			String query1 = "select userId from user where firstName=?&&lastName=?&&email=?&&phone=?&&userType=?";

			int userId = jdbcTemplate.queryForObject(query1, new Object[] { user.getFirstName(), user.getLastName(),
					user.getEmail(), user.getPhone(), user.getUserType() }, Integer.class);

			user.setUserId(userId);
			user.getLogin().setUserId(userId);
			
			System.out.println("user Id at userDao layer: "+user.getUserId());
			System.out.println("user ID through login in user dao layer :"+ user.getLogin().getUserId());
			
			if (0 != userId) {

				String query2 = "insert into address (addressLine1, addressLine2, city, state, zip, user_userId) values(?,?,?,?,?,?)";

				if (0 != jdbcOperations.update(query2,
						new Object[] { user.getAddress().getAddressLine1(), user.getAddress().getAddressLine2(),
								user.getAddress().getCity(), user.getAddress().getState(), user.getAddress().getZip(),
								user.getUserId() })) {

					String query3 = "insert into login (username, password, user_userId) values(?,?,?)";

					if (0 != jdbcOperations.update(query3, new Object[] { user.getLogin().getUsername(),
							user.getLogin().getPassword(), user.getLogin().getUserId() })) {

					String query5 = "select loginId from login where user_userID=?";
					int loginId = jdbcTemplate.queryForObject(query5, new Object[]{user.getLogin().getUserId()}, Integer.class);
					
					System.out.println("loginid at userDao from db : "+ loginId);
					user.getLogin().setLoginId(loginId);
					System.out.println("logind id through user :"+ user.getLogin().getLoginId());
					
						return userType;
					} else {
						return null;
					}
				}
			} else {
				return null;
			}

			return userType;

		} else {
			return null;
		}

	}

	public List<String> retrieveStates() {
		String query = "select * from state";

		List<UsStates> states = jdbcTemplate.query(query, new BeanPropertyRowMapper<UsStates>(UsStates.class));
		List<String> usStates = new ArrayList<String>();

		for (UsStates state : states) {
			//System.out.println(state.getCode() + " " + state.getName() + " " + state.getStateId());
			usStates.add(state.getName());
		}
		return usStates;

	}

	public List<String> retrieveUserType() {
		String query = "select * from usertype";

		List<UserType> usertype = jdbcTemplate.query(query, new BeanPropertyRowMapper<UserType>(UserType.class));
		List<String> userType = new ArrayList<String>();
		for (UserType user : usertype) {
			userType.add(user.getUserType());
		}
		for (String s : userType) {
			System.out.println(s + " at dao");
		}

		return userType;

	}

	public String verifyUser(Login login) {
		String query = "select user_userId from login where username=?&&password=?";
		int userId = 0;
		try {
			userId = jdbcTemplate.queryForObject(query, new Object[] { login.getUsername(), login.getPassword() },
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		System.out.println(userId);
		
		if (0 != userId) {
			String query1 = "select userType from user where userId=?";
			String userType = (String)jdbcTemplate.queryForObject(query1, new Object[]{userId}, String.class);
			System.out.println(userType);
			
			String query2 = "select loginId from login where user_userId=?";
			
			int loginId = jdbcTemplate.queryForObject(query2, new Object[]{userId}, Integer.class);
			login.setLoginId(loginId);
			
			System.out.println("loginid at dao :"+ login.getLoginId());
			return userType;
		} else {
			return null;
		}
	}
}
