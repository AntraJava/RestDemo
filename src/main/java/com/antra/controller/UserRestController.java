package com.antra.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import com.antra.model.User;
import com.antra.service.UserService;
import com.antra.util.ErrorResponse;
import com.antra.util.UserException;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	UserService userService;

	/**
	 * retrives all users
	 * 
	 * @throws UserException
	 **/
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() throws UserException {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {

			throw new UserException("no users available");
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * retrives single user
	 * 
	 * @throws UserException
	 **/
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) throws UserException {

		User user = userService.findById(id);
		if (user == null) {

			throw new UserException("user id with " + id + " not found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/** create a user **/
	@RequestMapping(value = "/user/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * update a user
	 * 
	 * @throws UserException
	 **/
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) throws UserException {

		User currentUser = userService.findById(id);

		if (currentUser == null) {

			throw new UserException("Unable to upate. User with id " + id + " not found.");
			
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	/**
	 * delete a user
	 * 
	 * @throws UserException
	 **/
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) throws UserException {

		User user = userService.findById(id);
		if (user == null) {
			
			throw new UserException("Unable to delete. User with id \" + id + \" not found.");
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
