package com.antra.service;

import java.util.List;

import com.antra.vo.PagedResponse;
import com.antra.vo.User;

public interface UserService {

		User findById(long id);

		User saveUser(User user);

		User updateUser(User user);

		void deleteUserById(long id);
	 
	    List<User> findAllUsers();

		PagedResponse<User> findPaginated(int page, int size, String orderBy);
	     
	    
}