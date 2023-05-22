
package com.customerRegistration.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.customerRegistration.model.User;

public interface CustomerProfileRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String loginId);

}
