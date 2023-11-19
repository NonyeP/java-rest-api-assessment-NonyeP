package com.cbfacademy.apiassessment.applicationDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.cbfacademy.apiassessment.applicationModel.Budget;
import com.cbfacademy.apiassessment.applicationModel.Users;


@Component
public class BudgetDAOService {

    /* create List<Budget> findAll(){}
	 * create Budget save(){}
	 * create Budget findOne(){}
	 * 
	 * 
	 * 
	 * and create a static list and use the BudgetDAO to talk to the static list
	 */

		
	private static List<Budget> items = new ArrayList<>();
	private static long itemCount = 0;
	private static Users user = new Users(123, "JohnDoe",LocalDate.now().plusMonths(3));
	     
	 static {
		items.add(new Budget(++itemCount,"Groceries",BigDecimal.valueOf(300),user));
		items.add(new Budget(++itemCount,"Entertainment",BigDecimal.valueOf(100),user));
		items.add(new Budget(++itemCount,"Utilities",BigDecimal.valueOf(500),user));
		items.add(new Budget(++itemCount,"Household Items",BigDecimal.valueOf(50),user));
	}
		
	    public List<Budget> getAllBudget(){//findsAll()
	        return items;
	    }
	   
	    public Budget findOne(Long id) {
	    	Predicate<? super Budget> predicate = items->items.getId().equals(id);
			return items.stream().filter(predicate).findFirst().orElse(null);
	    }

	     
	     
	    public Budget createBudget(Budget item) {//saves budget
	    	 item.setId(++itemCount);
	    	 items.add(item);
	    	 return item;
	    } 
	     
	    public Budget deleteBudgetById(Long id) {//deletes budget
	     	Predicate<? super Budget> predicate = items->items.getId().equals(id);
	     	items.removeIf(predicate);
	 		return items.stream().filter(predicate).findFirst().orElse(null);
	    }


}

