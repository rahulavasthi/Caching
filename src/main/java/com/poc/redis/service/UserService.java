package com.poc.redis.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.poc.redis.model.User;
import com.poc.redis.repository.UserRepository;

@Service
public class UserService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Cacheable(value = "users")
	public List<User> getAllUser() {
        LOG.info("Getting all users");
        return userRepository.findAll();
    }
	
	@CacheEvict(value = "users", allEntries = true)
	public void clearCache() {}
	

}
