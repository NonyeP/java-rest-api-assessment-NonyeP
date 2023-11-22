package com.cbfacademy.apiassessment.applicationController;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cbfacademy.apiassessment.applicationDAO.UsersDAOService;
import com.cbfacademy.apiassessment.applicationModel.Users;
import com.cbfacademy.apiassessment.exceptionHandlers.UserNotFoundException;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


/*
 * This userController class responsible for processing incoming REST API requests, 
 * preparing a model, and returning the view to be rendered as a response
 */




@RestController
public class UsersController {
    
    private UsersDAOService service;

	public UsersController(UsersDAOService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<Users> retrieveAllUsers(){
		return service.findAll();
		
	}

    @GetMapping("/users/{id}")
	public EntityModel<Users> retrieveUser(@PathVariable int id){
		/*
		 * This method returns an EntityModel wrapping a domain object and adding links to it.
		 * We use static MVC(a builder) to add a link to a specific method. we do not hard code link 
		 */
		Users user = service.findUser(id);
		
		if(user == null)
			throw new UserNotFoundException("id:" + id);
		EntityModel<Users> entityModel = EntityModel.of(user);
	
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// 
		entityModel.add(link.withRel("all-users"));//in case link changes in future
		return entityModel;
    }

    @PostMapping("/users")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
		/*
		 *The URI location sets the url of the user and id 
		 *replaced by new created id to return in addition to the
		 *response the url of the newly created user
		 */
		Users savedUser = service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())//
				.toUri();
		return ResponseEntity.created(null).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		Users user = service.findUser(id);  
		
		if(user == null)
			throw new UserNotFoundException("id:");
		service.deleteById(id);
		
	} 
	
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUserInfo(@PathVariable int id,@Valid @RequestBody Users userUpdated) {
		   Users user = service.updateById(id,userUpdated);
		   if(user == null)
		   throw new UserNotFoundException("id:");
	     return ResponseEntity.ok(user);
	         	
	}
	

		
    
}
