package com.poc.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.poc.hazelcast.model.User;
import com.poc.hazelcast.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class HazelcastApplication implements CommandLineRunner {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public HazelcastApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(HazelcastApplication.class, args);
	}

	@Override
	public void run(String... strings) {

		// Populating embedded database here
		LOG.info("Saving users. Current user count is {}.", userRepository.count());

		for (int i = 0; i < 1500; i++) {
			User shubham = new User("Shubham", 2000);
			User pankaj = new User("Pankaj", 29000);
			User lewis = new User("Lewis", 550);

			userRepository.save(shubham);
			userRepository.save(pankaj);
			userRepository.save(lewis);
		}
		LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	}
}
