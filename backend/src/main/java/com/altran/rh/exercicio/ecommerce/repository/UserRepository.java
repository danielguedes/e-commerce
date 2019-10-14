package com.altran.rh.exercicio.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.altran.rh.exercicio.ecommerce.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	@Query("{ 'email': ?0 }")
	Optional<User> findByEmail(final String email);
}