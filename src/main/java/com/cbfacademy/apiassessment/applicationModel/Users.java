package com.cbfacademy.apiassessment.applicationModel;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/*
 * This class creates users satisfies the inheritance criteria for the project by implementing the
 * insertUserId() method that enables a user to manually assign id
 */

public class Users implements UUIDGenerator{

    private Integer id;

    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;

    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;

    public Users(Integer id, @Size(min = 2, message = "Name should be at least 2 characters") String name,
            @Past(message = "Birthdate should be in the past") LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

    @Override
    public int insertUserId(UUID id, Users user) {
		return id.hashCode();
    }

    
    
}
