package com.antra.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.antra.entity.UserEntity;
import com.antra.model.User;

@Repository
public class CreateUserDao {
	
	@PersistenceContext
	 private EntityManager entityManager;
	
	
	UserEntity userEntity=new UserEntity();
	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public void saveUser(User user) {
		
		userEntity.setName(user.getName());
		userEntity.setAge(user.getAge());
		userEntity.setSalary(user.getSalary());
		System.out.println(entityManager);
		entityManager.persist(userEntity);
		
	}

}
