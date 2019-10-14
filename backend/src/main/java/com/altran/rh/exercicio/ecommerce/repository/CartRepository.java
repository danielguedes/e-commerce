package com.altran.rh.exercicio.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altran.rh.exercicio.ecommerce.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

	Optional<Cart> findByUserId(String id);
}