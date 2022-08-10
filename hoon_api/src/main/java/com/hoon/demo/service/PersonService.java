package com.hoon.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoon.demo.dto.PersonMongoDBRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonMongoDBRepository personMongoDBRepository;
	
}
