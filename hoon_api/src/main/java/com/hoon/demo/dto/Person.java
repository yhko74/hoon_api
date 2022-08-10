package com.hoon.demo.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection="person")
public class Person {
	@Field("name")
	private String name;
	
	private String job;

}
