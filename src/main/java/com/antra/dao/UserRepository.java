package com.antra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antra.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
