package com.customerRegistration.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.customerRegistration.model.User;

@Repository
public interface LoginRepository extends MongoRepository<User, String> {

	User findByUsername(String loginId);
}