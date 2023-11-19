package com.cbfacademy.apiassessment;

import java.util.UUID;

/*
 * This is an interface that generates id
 */

public interface UUIDGenerator {

    int insertUserId(UUID id, Users user);
    
	default int addUser(Users user) {
		UUID id = UUID.randomUUID();
		return insertUserId(id,user);
	}
}
