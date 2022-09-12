package com.webappdemo.www.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webappdemo.www.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
