package com.cbfacademy.apiassessment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * ResponseStatus imported to track not found Exception
 * also demonstrates the use of inheritance
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BudgetNotFoundException extends RuntimeException{
    
    public BudgetNotFoundException(String message){
        super(message);
    }
}
