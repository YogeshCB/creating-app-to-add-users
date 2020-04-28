package com.groferstest.webservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	 private static List<User> users = new ArrayList<>();
	 private static int userCount = 7;
	 
	 static {
		 users.add(new User(1, "Himanshu",new Date()));
		 users.add(new User(2, "jack",new Date()));
		 users.add(new User(3, "sam",new Date()));
		 users.add(new User(4, "curran",new Date()));
		 users.add(new User(5, "atul",new Date()));
		 users.add(new User(6, "ashish",new Date()));
		 users.add(new User(7, "dolly",new Date()));
	 }
	 
	 public List<User> findAll()
	 {
		  return users;
	 }
	 
	 public User save(final User user)
	 {
		 if (user.getId()==null)
		 {
			 user.setId(++userCount);
		 }
		 users.add(user);
		 return(user);
	 }
	 public User findOne(final int id )
	 {
		 for (final User user:users)
		 {
			 if(user.getId()==id)
			 {
				 return user;
			 }
		 }
		 return null;
	 }
	 public User deleteOne(final int id )
	 {
		 Iterator<User> iterator= users.iterator();
		 while(iterator.hasNext()) {
			 User user  = iterator.next(); 
			 if(user.getId()==id)
				 iterator.remove();
			 return user;
		 }
		 return null;
	 }
 }
