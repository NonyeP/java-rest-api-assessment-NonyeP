package com.cbfacademy.apiassessment;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UsersTest {
    private Users user;
    private Validator validator;

    @BeforeEach 
    public void setUp() {
        /*
         * initialize each user object and create validator instance
         */
        user = new Users(1,"John Doe",LocalDate.of(1990, 1, 1));
        user.setId(1);
        user.setName("John Doe");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testToString() {
         String expectedToString = "Users [id=1, name=John Doe, birthDate=1990-01-01]";
         Assertions.assertEquals(expectedToString, user.toString());
    }

    @Test
    public void testNameSizeValidation() {
        /*
         * Test @Size validation for name field and set name to violet @size constraints
         * then validate the user object
         */
        
         user.setName("J");

        var violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty(),"Name should violate @Size constraint");
    }

     @Test
    public void testBirthDatePastValidation() {
        /*
         * Test @Past validation for birthDate field and set birthDate to violet @Past constraints
         * then validate the user object
         */
        
        user.setBirthDate(LocalDate.now().plusDays(1));
        
        var violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty(),"BirthDate should violate @Past constraint");
    }

}
