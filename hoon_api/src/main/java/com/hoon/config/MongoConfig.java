package com.hoon.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private String port;
	@Value("${spring.data.mongodb.database}")
	private String database;
	@Value("${spring.data.mongodb.username}")
	private String userName;
	@Value("${spring.data.mongodb.password}")
	private String password;
	
		
	@Bean
	public MongoClient mongo() throws Exception {
	    MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());

	    return new MongoClient(new ServerAddress(host, Integer.parseInt(port)), Arrays.asList(credential));
	}
	
	 @Bean(name="mongoTemplate")
	  public MongoTemplate mongoTemplateConfig() throws Exception {
	    
	    return new MongoTemplate(mongo(), database);
	  }

}
