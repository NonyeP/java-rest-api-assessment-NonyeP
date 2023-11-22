package com.cbfacademy.apiassessment.applicationController;

import java.net.URI;
import java.time.LocalDate;
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

import com.cbfacademy.apiassessment.applicationDAO.BudgetDAOService;
import com.cbfacademy.apiassessment.applicationModel.Budget;

import com.cbfacademy.apiassessment.exceptionHandlers.BudgetNotFoundException;
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
	
	@GetMapping("/budgets")
	public List<Budget> retrieveAllBudget(){
		return service.getAllBudget();
		
	}

	@GetMapping("/budgets/{id}")
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
	
	
	@PostMapping("/budgets")
	public ResponseEntity<Budget> createUser(@Valid @RequestBody Budget items){
		Budget savedItem = service.createBudget(items);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedItem.getId())
				.toUri();
		return ResponseEntity.created(null).build();
	}
	
	@DeleteMapping("/budgets/{id}")
	public void deleteitem(@PathVariable long id){
		Budget user = service.findOne(id);  
		
		if(user == null)
			throw new UserNotFoundException("id:");
		service.deleteBudgetById(id);
		
	}

    
    @GetMapping("/budgets/{date}")
	public EntityModel<Budget> retrieveBudget(@PathVariable LocalDate date){
		/*
		 * This method returns an EntityModel wrapping a domain object and adding links to it.
		 * We use static MVC(a builder) to add a link to a specific method. we do not hard code link 
		 */
		Budget budget = service.findBudgetByDate(date);
		
		if(budget == null)
			throw new BudgetNotFoundException("date:" + date);
		EntityModel<Budget> entityModel = EntityModel.of(budget);
	
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllBudget());// 
		entityModel.add(link.withRel("all-users"));//in case link changes in future
		return entityModel;
    }

    @PutMapping("/budgets/{category}")
	public ResponseEntity<Budget> updateBudgetByCategory(@PathVariable String category,@Valid @RequestBody Budget budgetUpdated) {
		   Budget budget = service.updateBudgetByCategory(category,budgetUpdated);
		   if(budget == null)
		   throw new BudgetNotFoundException("category:");
	     return ResponseEntity.ok(budget);
	         	
	}
    
    
}
