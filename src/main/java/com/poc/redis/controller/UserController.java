package com.poc.redis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.redis.model.User;
import com.poc.redis.repository.UserRepository;
import com.poc.redis.service.UserService;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
	UserRepository userRepository;
    
    @Autowired
	private UserService userService;

    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findById(Long.valueOf(userId)).get();
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        LOG.info("deleting person with id {}", userId);
        userRepository.deleteById(userId);
    }
    
    @PostMapping("/create")
    public boolean createPerson(@RequestBody User user) {
        userRepository.save(user);
        return true;
    }
    
    @GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		System.out.println(System.currentTimeMillis());
		List<User> users= userService.getAllUser();
		System.out.println(System.currentTimeMillis());
        return users;
    }
}
