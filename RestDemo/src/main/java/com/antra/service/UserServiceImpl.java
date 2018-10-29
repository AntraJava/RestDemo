package com.antra.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.antra.dao.UserRepository;
import com.antra.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antra.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    public List<User> findAllUsers() {
        List<UserEntity> users =  userRepo.findAll();
        return users.stream().map(e -> new User(e.getId(),e.getName(),e.getAge(),e.getSalary())).collect(Collectors.toList());
    }
     
    public User findById(long id) {
//        for(User user : users){
//            if(user.getId() == id){
//                return user;
//            }
//        }
        return null;
    }
     
    public User findByName(String name) {
//        for(User user : users){
//            if(user.getName().equalsIgnoreCase(name)){
//                return user;
//            }
//        }
        return null;
    }
     
    public void saveUser(User user) {

    	userRepo.save(new UserEntity(user.getId(),user.getName(),user.getAge(),user.getSalary()));
       /* user.setId(counter.incrementAndGet());
        users.add(user);*/
    }
 
    public void updateUser(User user) {
//        int index = users.indexOf(user);
//        users.set(index, user);
    }
 
    public void deleteUserById(long id) {
         
//        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//            User user = iterator.next();
//            if (user.getId() == id) {
//                iterator.remove();
//            }
//        }
    }
 
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }
     
    public void deleteAllUsers(){
//        users.clear();
    }

}
