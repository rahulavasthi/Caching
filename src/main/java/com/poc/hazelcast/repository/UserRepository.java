package com.poc.hazelcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.hazelcast.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
