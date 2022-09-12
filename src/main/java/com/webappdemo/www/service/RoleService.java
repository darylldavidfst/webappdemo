package com.webappdemo.www.service;

import java.util.ArrayList;
import java.util.List;

import com.webappdemo.www.entity.Role;

public interface RoleService {

	List<Role> findAll();

	Role findOne(int id);

	Role findByName(String name);

	void deleteAll();

	void fillRoles();

	ArrayList<Integer> getRolesIds();
}
