package com.antra.service;

import java.util.List;

import com.antra.model.User;

public interface UserService {

		User findById(long id);
	     
	    void saveUser(User user);
	     
	    void updateUser(User user);
	     
	    void deleteUserById(long id);
	 
	    List<User> findAllUsers();   
	     
	    
}