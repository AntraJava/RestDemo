package com.antra.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antra.dao.CreateUserDao;
import com.antra.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	private static final AtomicLong counter = new AtomicLong();
    
    private static List<User> users;
     
    @Autowired
    private CreateUserDao createUserDao;
   /* static{
        users= populateDummyUsers();
    }*/
 
    public List<User> findAllUsers() {
        return users;
    }
     
    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
     
    public User findByName(String name) {
        for(User user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }
     
    public void saveUser(User user) {
    	
    	createUserDao.saveUser(user);
       /* user.setId(counter.incrementAndGet());
        users.add(user);*/
    }
 
    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }
 
    public void deleteUserById(long id) {
         
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }
     
    public void deleteAllUsers(){
        users.clear();
    }
 
    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
      
        users.add(new User(new Long(counter.incrementAndGet()),"Sam",new Integer(30), new Double(70000)));
        users.add(new User(new Long(counter.incrementAndGet()),"Tom",new Integer(40), new Double(50000)));
        users.add(new User(new Long(counter.incrementAndGet()),"Jerome",new Integer(45), new Double(30000)));
        users.add(new User(new Long(counter.incrementAndGet()),"Silvia",new Integer(50), new Double(40000)));
        return users;
    }
}
