package com.hoon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String redisHost;
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(30);
		jedisPoolConfig.setMinIdle(10);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		
		return jedisPoolConfig;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
	    jedisConFactory.setHostName(redisHost);
	    jedisConFactory.setPort(redisPort);
	    return jedisConFactory;
	}
	
	 @Bean(name="redisTemplate")
	  public RedisTemplate redisTemplateConfig(JedisConnectionFactory jedisConnectionFactory) {
	    RedisTemplate redisTemplate = new RedisTemplate();
	 
	    redisTemplate.setKeySerializer(new StringRedisSerializer());
	    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	    redisTemplate.setConnectionFactory(jedisConnectionFactory);
	 
	    return redisTemplate;
	  }

}
