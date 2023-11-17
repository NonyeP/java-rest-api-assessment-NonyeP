package com.cbfacademy.apiassessment;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/* This class is a simple POJO class
 * 
 * 
 * 
 */

public class Expenses {
	/* constraints are set for amount to be positive or zero and 
	 * for local date to be in the future
	 * 
	 */
	@NotNull
	@DecimalMin(value = "0")
    private BigDecimal amount;

	@Future(message = "Date should be in the future")
    private LocalDate date;

    private String expenseDescription;
    private Integer id;
    

    public Expenses(BigDecimal amount, LocalDate date, String expenseDescription, Integer id) {
		super();
		this.amount = amount;
		this.date = date;
		this.expenseDescription = expenseDescription;
		this.id = id;
	}
	public Expenses() {
		super();
		
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
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Expense [amount=" + amount + ", date=" + date + ", expenseDescription=" + expenseDescription + ", id="
				+ id + "]";
	}
	

}
