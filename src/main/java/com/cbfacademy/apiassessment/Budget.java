package com.cbfacademy.apiassessment;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/*
 * This class is a POJO class
 * 
 * 
 */

public class Budget {
    /* constraints are set for amount to be positive or zero and 
	 * for local date to be in the future
	 * 
	 */
	@NotNull
	@DecimalMin(value = "0")
    private BigDecimal amount;

    @Future(message = "Date should be in the past")
    private LocalDate date;
    private String budgetCategory;
    private Integer id;



    public Budget(Integer id, LocalDate date, String budgetCategory, BigDecimal amount) {
        this.amount = amount;
        this.date = date;
        this.budgetCategory = budgetCategory;
        this.id = id;
    }

    public Budget() {

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Budget [id=" + id + ", budgetCategory=" + budgetCategory + ", amount=" + amount + "]";
    }

    
    
}
