package com.antra.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.antra.dao.UserRepository;
import com.antra.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.antra.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAllUsers() {
		List<UserEntity> users = userRepo.findAll();
		/*
		 * ArrayList al=new ArrayList();
		 * 
		 * for(int i=0;i<users.size();i++) { User user=new User(); UserEntity
		 * userEntity=(UserEntity)users.get(i); user.setId(userEntity.getId());
		 * user.setName(userEntity.getName()); user.setAge(userEntity.getAge());
		 * user.setSalary(userEntity.getSalary()); al.add(user); } return al;
		 */

		return users.stream().map(e -> new User(e.getId(), e.getName(), e.getAge(), e.getSalary()))
				.collect(Collectors.toList());
	}

	public User findById(long id) {

		UserEntity userEntity = userRepo.findOne(id);

		if (userEntity != null) {
			User user = new User();
			user.setId(userEntity.getId());
			user.setName(userEntity.getName());
			user.setAge(userEntity.getAge());
			user.setSalary(userEntity.getSalary());
			return user;
		} else {
			return null;
		}

	}

	public void saveUser(User user) {

		userRepo.save(new UserEntity(user.getId(), user.getName(), user.getAge(), user.getSalary()));
	}

	public void updateUser(User user) {

		userRepo.saveAndFlush(new UserEntity(user.getId(), user.getName(), user.getAge(), user.getSalary()));

	}

	public void deleteUserById(long id) {
		//userRepo.findAll(new PageRequest(1,2));
		userRepo.delete(id);
	}

	public List findPaginated(int page, int size, String orderBy) {

		Sort sort = null;
		if (orderBy != null) {
			sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		Page<UserEntity> page1 = userRepo.findAll(new PageRequest(page, size, sort));
		List<UserEntity> list = page1.getContent();

		return list.stream().map(e -> new User(e.getId(), e.getName(), e.getAge(), e.getSalary()))
				.collect(Collectors.toList());

	}
}