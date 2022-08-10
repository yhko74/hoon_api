package com.hoon.demo.dto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoDBRepository extends MongoRepository<Person, String> {
	public Person findByName(String name);
}
