package com.poc.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.redis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
