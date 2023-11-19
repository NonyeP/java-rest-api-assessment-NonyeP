package com.cbfacademy.apiassessment.applicationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/*
 * This class is a POJO class that toString(), setters, getters methods and and constructors are created in. 
 * It also defines the method that will be used in its service class
 * 
 */

public class Budget {
    /* constraints are set for amount to be positive or zero and 
	 * for local date to be in the future and for the warning thres
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/* constraints are set for amount to be positive or zero and 
	 * for local date to be in the future,for string to be 
	 * 
	 */
	private String budgetCategory;
	
	@NotNull
	@DecimalMin(value = "0")
	private BigDecimal amount;

    @NotNull
	@DecimalMin(value = "0")
    private BigDecimal warningThreshold;

	
	@Future(message = "Date should be in the future")
	private LocalDate date;
	private Users user;
	
	
	public Budget(Long id, String budgetCategory, BigDecimal amount, BigDecimal warningThreshold, Users user) {
		super();
		this.id = id;
		this.budgetCategory = budgetCategory;
		this.amount = amount;
		this.user = user;
        this.warningThreshold = warningThreshold;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBudgetCategory() {
		return budgetCategory;
	}

	public void setBudgetCategory(String budgetCategory) {
		this.budgetCategory = budgetCategory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Budget() {
		
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", User= " + user + ", budgetCategory=" + budgetCategory + ", amount=" + amount + "]";
	}
}
	