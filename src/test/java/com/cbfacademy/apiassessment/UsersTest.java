package com.cbfacademy.apiassessment;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cbfacademy.apiassessment.applicationModel.Users;

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

    @Test
    public void testSetters() {
        /*
         * Test setters 
         */
        
        user.setBirthDate(LocalDate.now().plusDays(1));
        user.setId(1);
        user.setName("Jane Doe");
           
        Assertions.assertEquals("1980-01-01", user.getBirthDate());
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testInsertUserIdMethod() {
        /*
         * Test interface insertUserIdMethod()
         * create user,create mock user, set user and 
         * generate UUID.
         * call the insertUserId() method and then assert
         */
        
        Users user = new Users(1,"Jane Doe",LocalDate.now().minusYears(30));
        UUID userId = UUID.randomUUID();
        

        user.setId(1);
        user.setName("Jane Doe");
        user.setBirthDate(LocalDate.now().minusYears(30));
        
        Users userMock = new Users(16,"Jane Doe",LocalDate.now().minusYears(30));
        userMock.setId(16);
        userMock.setName("Joe Doe");
        userMock.setBirthDate(LocalDate.now().minusYears(12));
        
        Integer result = user.insertUserId(userId, user);
        Integer expectedResult = userId.hashCode();
        
        Assertions.assertEquals(expectedResult,result);

    }

}
