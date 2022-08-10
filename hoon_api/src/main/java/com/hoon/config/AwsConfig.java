package com.hoon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
	
	@Value("${aws.region}")
	private String region;
	
	@Autowired
	private DefaultAWSCredentialsProviderChain credentialsProvider;
	
	@Bean(name="credentialsProvider")
	public DefaultAWSCredentialsProviderChain getProdiver() {
		DefaultAWSCredentialsProviderChain credentialsProviderChain = new DefaultAWSCredentialsProviderChain();
		AWSCredentials creds = credentialsProviderChain.getCredentials();
		
		System.out.println("credentialsProviderChain create : " + credentialsProviderChain);
		System.out.println("creds info : id " + creds.getAWSAccessKeyId() );
		
		return credentialsProviderChain;
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		//DefaultAWSCredentialsProviderChain credentialsProviderChain = new DefaultAWSCredentialsProviderChain();
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(credentialsProvider)
	            .withRegion(region)
				.build();
		
		/*  local dynamodb 설정
		amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        	// we can use any region here
             new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();

            // use the DynamoDB API over HTTP
            listTables(amazonDynamoDB.listTables(), "DynamoDB Local over HTTP");
		*/
		System.out.println("amazonDynamoDB create : " + amazonDynamoDB);
		return amazonDynamoDB;
	}
	
	@Bean
	public DynamoDBMapper dynamoDBMapper( AmazonDynamoDB amazonDynamoDB ) {
		
		DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
		        .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
		       // .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
		        .withTableNameOverride(null)
		        .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
		    .build();
	        
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
		System.out.println("dynamoDBMapper create : " + dynamoDBMapper);
		return dynamoDBMapper;
	}
	
	@Bean
	public AmazonS3 getAmazonS3() {
		
		//DefaultAWSCredentialsProviderChain credentialsProviderChain = new DefaultAWSCredentialsProviderChain();
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withCredentials(credentialsProvider)
				.withRegion(region)
				.build();
		
		return s3;
	}
	
//	 public static void listTables(ListTablesResult result, String method) {
//	        System.out.println("found " + Integer.toString(result.getTableNames().size()) + " tables with " + method);
//	        for(String table : result.getTableNames()) {
//	            System.out.println(table);
//	        }
//	    }
	
}
