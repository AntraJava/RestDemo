package com.antra.dao;

import com.antra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByAgeBetween(int from, int to);

    UserEntity findByAgeAndName(int age, String name);
}
