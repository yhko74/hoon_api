package com.hoon.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.hoon")
public class ElasticSearchConfiguration {
	
	 @Bean
	 public Client client() throws UnknownHostException {
	    Settings settings = Settings.builder()
	            .put("client.transport.sniff", true)
	            .put("cluster.name", "elasticsearch").build();
	    @SuppressWarnings("resource")
	    TransportClient client = new PreBuiltTransportClient(settings)
	            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9200));
	
	    return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
	    return new ElasticsearchTemplate(client());
   }
}

