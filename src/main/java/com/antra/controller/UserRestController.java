package com.antra.controller;

import javax.validation.Valid;


import com.antra.exception.UserNotFoundException;
import com.antra.util.Constants;
import com.antra.vo.PagedResponse;
import com.antra.vo.ResponseMessage;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import com.antra.vo.User;
import com.antra.service.UserService;
import com.antra.vo.ErrorResponse;
import com.antra.exception.UserException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "REST API for Users", tags={"User"})
public class UserRestController {

	private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

	UserService userService;

	Constants messages;

	@Autowired
	public UserRestController(UserService userService,Constants messages) {
		this.userService = userService;
		this.messages = messages;
	}
	/**
	 * retrives single user
	 * 
	 **/
	@ApiOperation(value = "gets a single user")
	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("uid") long id) throws UserException {
		User user = userService.findById(id);
		if (user == null) {
			throw new UserNotFoundException(messages.getMessage("USER_NOT_FOUND"));
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//http://localhost:8009/swagger-ui.html#/
	/**
	 *  Get user by using pagination, if no parameters are provided, the first page with 10 records will be returned
	 *
	 **/
	@ApiOperation(value = "get users accordingly")
	@RequestMapping(value = "/user",  method = RequestMethod.GET)
	public ResponseEntity<PagedResponse<User>> getUserPagenation(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
														   @RequestParam(required = false, defaultValue = "5") Integer rows,
														   @RequestParam(required = false, defaultValue = "name") String orderBy) {

		PagedResponse<User> users = userService.findPaginated(pageNo, rows, orderBy);
		if (users.isEmpty()) {
			throw new UserNotFoundException(messages.getMessage("USER_NOT_FOUND"));
		}
		return new ResponseEntity<PagedResponse<User>>(users, HttpStatus.OK);

	}

	/** create a user **/
	@ApiOperation(value = "create a user")
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
		User savedUser = userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("USER_CREATED"),savedUser), headers, HttpStatus.CREATED);
	}

	/**
	 * update a user
	 * 
	 **/
	@ApiOperation(value = "update a user")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			throw new UserNotFoundException(messages.getMessage("USER_NOT_FOUND"));
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
	@ApiOperation(value = "delete a user")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("id") long id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new UserNotFoundException(messages.getMessage("USER_NOT_FOUND"));
		}
		userService.deleteUserById(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("USER_DELETED"),user), HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		logger.error("Controller Error",ex);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerUserNotFound(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
