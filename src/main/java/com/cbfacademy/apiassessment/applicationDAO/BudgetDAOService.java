package com.cbfacademy.apiassessment.applicationDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;

import com.cbfacademy.apiassessment.applicationModel.Budget;
import com.cbfacademy.apiassessment.applicationModel.Users;


@Component
public class BudgetDAOService {

    /* create List<Budget> findAll(){}
	 * create Budget save(){}
	 * create Budget findOne(){}
	 * search Budget by category
     * delete Budget
	 * 
	 * 
	 * and create a static list and use the BudgetDAO to talk to the static list
	 */

		
	 private static List<Budget> items = new ArrayList<>();
	     private static long itemCount = 0;
	     private static Users user = new Users(123, "JohnDoe",LocalDate.now().plusMonths(3));
	     
	     static {
		   items.add(new Budget(++itemCount,"Groceries",BigDecimal.valueOf(300), BigDecimal.valueOf(250),LocalDate.now(),LocalDate.now(),user));
		   items.add(new Budget(++itemCount,"Entertainment",BigDecimal.valueOf(100), BigDecimal.valueOf(300),LocalDate.now().minusDays(15),LocalDate.now().plusDays(30),user));
		   items.add(new Budget(++itemCount,"Utilities",BigDecimal.valueOf(500), BigDecimal.valueOf(900),LocalDate.now().minusDays(15),LocalDate.now().plusDays(30),user));
		   items.add(new Budget(++itemCount,"Household Items",BigDecimal.valueOf(50), BigDecimal.valueOf(200),LocalDate.now().minusDays(15),LocalDate.now().plusDays(30),user));
	     }
		
	    public List<Budget> getAllBudget(){//findsAll()
	        return items;
	    }
	   
	    public Budget findBudgetByCategory(String category) {
	    	Predicate<? super Budget> predicate = items->items.getBudgetCategory().equals(category);
			return items.stream().filter(predicate).findFirst().orElse(null);
	    }

        public Budget findBudgetByDate(LocalDate date) {
	    	Predicate<? super Budget> predicate = items->items.getDateOfEntry().equals(date);
			return items.stream().filter(predicate).findFirst().orElse(null);
	    }

        public List<Budget> findAllInCategory(String category) {
	    	Predicate<? super Budget> predicate = items->items.getBudgetCategory().equals(category);
			return items.stream().filter(predicate).collect(Collectors.toList());
	    }

	     
	     
	    public Budget createBudget(Budget item) {//saves budget
            item.validateBudget();
            item.setId(++itemCount);
	    	 items.add(item);
	    	 return item;
	    } 
	     
	    public Budget deleteBudgetById(Long id) {//deletes budget
	     	Predicate<? super Budget> predicate = items->items.getId().equals(id);
	     	items.removeIf(predicate);
	 		return items.stream().filter(predicate).findFirst().orElse(null);
	    }

        public Budget deleteBudgetByCategory(String id) {//deletes budget
	     	Predicate<? super Budget> predicate = items->items.getId().equals(id);
	     	items.removeIf(predicate);
	 		return items.stream().filter(predicate).findFirst().orElse(null);
	    }

        public Budget findOne(Long id) {
	    	Predicate<? super Budget> predicate = items->items.getId().equals(id);
			return items.stream().filter(predicate).findFirst().orElse(null);
	    }

        public Budget updateBudgetByCategory(String category, Budget updatedBudget) {
            for (Budget budget : items) {
                if (budget.getBudgetCategory().equals(category)) {
                    budget.setAmount(updatedBudget.getAmount()); 
                    return budget; 
                }
            }
            return null;
        }

        public void updateBudget(Budget updatedBudget) {
            for (Budget budget : items) {
                if (budget.getId().equals(updatedBudget.getId())) {
                    budget.setBudgetCategory(updatedBudget.getBudgetCategory());
                    budget.setAmount(updatedBudget.getAmount());
                    budget.setWarningThreshold(updatedBudget.getWarningThreshold());
                    budget.validateBudget(); 
                    break;
                }
            }
        }



      

        public List<Budget> getProjectsApproachingDate(LocalDate currentDate, int daysLeftToBudgetDate){
            List<Budget> approachingBudgets = new ArrayList<>();
            for (Budget budget : items) {
              if (budget.getDate() != null) {
                long daysUntilBudget = ChronoUnit.DAYS.between(currentDate, budget.getDate());
                if (daysUntilBudget <= daysLeftToBudgetDate && daysUntilBudget >= 0) {
                    budget.setApproachingDateWarning(true);
                    budget.setWarningMessage("Warning: Approaching budget date!");
                } else {
                    budget.setApproachingDateWarning(false);
                    budget.setWarningMessage(null);
                }
              }
            
            }
            return approachingBudgets;
    }

  

}

