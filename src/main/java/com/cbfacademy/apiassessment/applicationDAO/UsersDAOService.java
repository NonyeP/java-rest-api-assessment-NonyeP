package com.cbfacademy.apiassessment.applicationDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.cbfacademy.apiassessment.applicationModel.Users;

/*
 * UserDAOServices talks to a static list
 * here I created a findAll() method that returns a list of users
 * (List<User>), a saveUser(),findUser() and a deleteById()
 * methods.
 * 
 */

@Component
public class UsersDAOService {
    
   private static List<Users> users = new ArrayList<>();
   private static int usersCount = 0; 
    

   static {
	   users.add(new Users(++usersCount,"Adam",LocalDate.now().minusYears(30)));
	   users.add(new Users(++usersCount,"Eve",LocalDate.now().minusYears(23)));
	   users.add(new Users(++usersCount,"Jane",LocalDate.now().minusYears(15)));
	   users.add(new Users(++usersCount,"John",LocalDate.now().minusYears(64)));
    }
    
    public List<Users> findAll(){
        return users;
    }
   
    public Users findUser(int id) {
        /*
         * This method finds a user based on id.It returns an optional so there is a need to convert it. 
         * To avoid a nullpointer exception when it returns null we introduced the orElse() method
         * 
         */
    	Predicate<? super Users> predicate = users->users.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public Users saveUser(Users user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    } 
    
    public Users deleteById(int id) {
        Predicate<? super Users> predicate = users->users.getId().equals(id);
        users.removeIf(predicate);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public Users updateById(int id, Users updatedUser) {//sets users name
        int index = algoSearchById(id);
        updatedUser = users.set(index, updatedUser);
        return updatedUser;
   }
 
    public int algoSearchById(int id) {
     /*
      * This method uses a binary search algorithm to find a user by its ID.
      * It returns -1 if User with given ID is not found.
      */
       List<Integer> ids = new ArrayList<>();
       for (Users userOne : users) {
         ids.add(userOne.getId());
       }
       int left = 0;
       int right = ids.size() - 1;

       while (left <= right) {
         int mid = left + (right - left) / 2;
          if (ids.get(mid).equals(id)) {
          return mid;
          } else if (ids.get(mid) < id) {
            left = mid + 1;
          } else {
            right = mid - 1;
          }
       }
        return -1; // Return -1 if User with given ID is not found
    }

    
}
