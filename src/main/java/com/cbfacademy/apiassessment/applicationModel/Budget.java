package com.cbfacademy.apiassessment.applicationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private LocalDate dateOfEntry;


    private static final Logger logger = LoggerFactory.getLogger(Budget.class);
    private boolean approachingDateWarning;
    private boolean approachingBudgetWarning;
    
    
    
    private String warningMessage;
	
	
	public Budget(Long id, String budgetCategory, BigDecimal amount, BigDecimal warningThreshold, LocalDate dateOfEntry, LocalDate date, Users user) {
		super();
        
		this.id = id;
		this.budgetCategory = budgetCategory;
		this.amount = amount;//amount specified for budget
		this.user = user;
        
        this.date = date;
        this.dateOfEntry = dateOfEntry;
        setWarningThreshold(warningThreshold);
        this.warningMessage = isWarningMessage();
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
    

	public BigDecimal getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(BigDecimal warningThreshold) {
        if (warningThreshold != null && amount.compareTo(warningThreshold) > 0) {
            this.warningThreshold = amount;
            triggerWarning();
        } else {
        this.warningThreshold = warningThreshold;
        }
    }

    public void triggerWarning() {
        logger.warn("Warning: Warning threshold exceeds the budget amount!");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (dateOfEntry.isAfter(date)){
          setWarningMessage(warningMessage);
        }else{
            this.date = dateOfEntry;
        }
    }

    @Override
    public String toString() {
        return "Budget [id=" + id + ", budgetCategory=" + budgetCategory + ", Date of Entry=" + dateOfEntry + ", Date of BudgetEnding=" + date + "amount=" + amount + ", warningThreshold="
                + warningThreshold + ", date=" + date + ", user=" + user + "]";
    }

    public void validateBudget() {
            
        if (warningThreshold != null && warningThreshold.compareTo(amount) > 0) {
            this.warningThreshold = amount;
        }
    }

    public static Logger getLogger() {
        return logger;
    }
    public boolean isApproachingDateWarning() {
        long daysBetween = ChronoUnit.DAYS.between(dateOfEntry, date);
        if(daysBetween <= 2) {
            this.approachingDateWarning = true;
        }
        return this.approachingDateWarning;
    }

    public void setApproachingDateWarning(boolean approachingDateWarning) {
       long daysBetween = ChronoUnit.DAYS.between(dateOfEntry, date);
        if(daysBetween <= 2) {
            this.approachingDateWarning = true;
        }else{

        this.approachingDateWarning = false;
        }
    }

    public boolean isApproachingBudgetWarning() {
        double amountConverted = getAmount().doubleValue();
        double warningAmountConverted = getWarningThreshold().doubleValue();
        double difference =warningAmountConverted - amountConverted;
        if(difference < 10)
         {
            this.approachingBudgetWarning = true;
        }
        return this.approachingBudgetWarning;
        
    }
     
    public String getWarningMessage() {
        return warningMessage;
    }

    

    public void setWarningMessage(String warningMessage) {
    
           this.warningMessage = warningMessage;
        
    }

    public String isWarningMessage() {
        double amountConverted = getAmount().doubleValue();
        double warningAmountConverted = getWarningThreshold().doubleValue();
        double difference =warningAmountConverted - amountConverted;
        if(difference < 10)
         {
            triggerWarning();
          return  this.warningMessage = "true:Warning budget is almost exceeded ";
            
        }
        return this.warningMessage = "false: within budget";
        
    }

    public LocalDate getDateOfEntry() {
        
        return this.dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
        
    }

    public void validateDateOfEntry() {
        if (dateOfEntry.isAfter(date)){
          setWarningMessage(warningMessage);
        }else{
            this.dateOfEntry = dateOfEntry;
        }
    }
    
}
	