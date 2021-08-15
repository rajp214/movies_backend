package com.dvstore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dvstore.model.User;

public interface UsersRepository extends MongoRepository<User, String> {
	  User findByEmail(String username);
}