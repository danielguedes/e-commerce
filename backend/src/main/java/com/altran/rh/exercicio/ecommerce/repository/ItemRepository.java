package com.altran.rh.exercicio.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altran.rh.exercicio.ecommerce.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
}