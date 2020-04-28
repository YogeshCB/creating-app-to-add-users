package com.groferstest.webservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
		@Autowired
		private UserDao service;
		
		@RequestMapping(method= RequestMethod.GET, path ="/users")
	 	public List<User> retrieveAllUser()
	 	{
	 		return service.findAll();
	 	}
		
		@RequestMapping(method= RequestMethod.GET, path ="/users/{id}")
		public Resource<User> retriveUser (@PathVariable int id )
		{
			User user = service.findOne(id);
			if(user == null)
			throw new UserNotFoundException("id" + id);
				
			Resource<User> resource = new Resource<>(user);
				
			ControllerLinkBuilder linkTo =  linkTo(methodOn(this.getClass()).retrieveAllUser());
				
			resource.add(linkTo.withRel("all-users"));
			
			return resource;
			
		}
		
		
		@RequestMapping(method= RequestMethod.POST, path ="/users")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
		{
			User savedUser = service.save(user);
			
			URI location = ServletUriComponentsBuilder 
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		@RequestMapping(method= RequestMethod.DELETE, path ="/users/{id}")
		public User deleteUser (@PathVariable int id )
		{
			User user = service.deleteOne(id);
			if(user == null)
			{
				throw new UserNotFoundException("id" + id);
			}
			return user;
			
		}
}

