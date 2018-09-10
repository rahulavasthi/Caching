package com.poc.hazelcast.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.poc.hazelcast.model.User;
import com.poc.hazelcast.repository.UserRepository;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Cacheable()
	public List<User> getAllUser() {
        LOG.info("Getting all users");
        return userRepository.findAll();
    }
	
	@CacheEvict(allEntries = true)
	public void clearCache() {}
	

}
