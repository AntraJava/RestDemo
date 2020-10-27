package com.antra.service;

import com.antra.dao.UserRepository;
import com.antra.entity.UserEntity;
import com.antra.util.UserEntityConverter;
import com.antra.vo.PagedResponse;
import com.antra.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
		UserEntity userEntity = userRepo.findById(id).get();
		return UserEntityConverter.convertEntityToUser(userEntity);
	}

	@Transactional
	public User saveUser(User user) {
		UserEntity userEntity = userRepo.save(new UserEntity(user.getId(), user.getName(), user.getAge(), user.getSalary()));
		return UserEntityConverter.convertEntityToUser(userEntity);
	}

	public User updateUser(User user) {
		UserEntity userEntity = userRepo.saveAndFlush(new UserEntity(user.getId(), user.getName(), user.getAge(), user.getSalary()));
		return UserEntityConverter.convertEntityToUser(userEntity);
	}

	public void deleteUserById(long id) {
		//userRepo.findAll(new PageRequest(1,2));
		userRepo.deleteById(id);
	}

	public PagedResponse<User> findPaginated(int page, int size, String orderBy) {

		Sort sort = null;
		if (orderBy != null) {
			sort = Sort.by(Sort.Direction.ASC, orderBy);
		}
		Page<UserEntity> page1 = userRepo.findAll(PageRequest.of(page, size, sort));
		List<UserEntity> list = page1.getContent();
		PagedResponse<User> result = new PagedResponse<>();
		result.setPage(page1.getNumber());
		result.setRows(page1.getSize());
		result.setTotalPage(page1.getTotalPages());
		result.setTotalElement(page1.getTotalElements());
		result.setOrder(page1.getSort().toString());
		result.setBody(list.stream().map(e -> new User(e.getId(), e.getName(), e.getAge(), e.getSalary()))
				.collect(Collectors.toList()));
		return result;
	}
}
