package com.webappdemo.www.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webappdemo.www.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}