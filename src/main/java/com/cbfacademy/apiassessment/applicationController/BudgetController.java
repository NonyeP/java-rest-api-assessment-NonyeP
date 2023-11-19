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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cbfacademy.apiassessment.applicationDAO.BudgetDAOService;
import com.cbfacademy.apiassessment.applicationModel.Budget;
import com.cbfacademy.apiassessment.exceptionHandlers.UserNotFoundException;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


/*
 * This class marks as a request handler to allow Spring to recognize 
 * it as a RESTful service during runtime.
 * Class contains a static wrapped method retrieveItem
 */
@RestController
public class BudgetController {

    private BudgetDAOService service;

	public BudgetController(BudgetDAOService service) {
		this.service = service;
	}
	
	@GetMapping("/items-budget")
	public List<Budget> retrieveAllBudget(){
		return service.getAllBudget();
		
	}

	@GetMapping("/items-budget/{id}")
	public EntityModel<Budget> retrieveItem(@PathVariable long id){
		Budget item = service.findOne(id);
		
		if(item == null)
			throw new UserNotFoundException("id:" + id);
		EntityModel<Budget> entityModel = EntityModel.of(item);//
		//and creating the entity object
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllBudget());
		entityModel.add(link.withRel("all-budget"));
		return entityModel;
		
	}
	
	
	@PostMapping("/items-budget")
	public ResponseEntity<Budget> createUser(@Valid @RequestBody Budget items){
		Budget savedItem = service.createBudget(items);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedItem.getId())
				.toUri();
		return ResponseEntity.created(null).build();
	}
	
	@DeleteMapping("/items-budget/{id}")
	public void deleteitem(@PathVariable long id){
		Budget user = service.findOne(id);  
		
		if(user == null)
			throw new UserNotFoundException("id:");
		service.deleteBudgetById(id);
		
	}

    
}
