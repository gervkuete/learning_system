package com.gervkuete.dao;

import com.gervkuete.model.User;

public interface UserDAO {
	
	User findUserByEmail(String email);

}
