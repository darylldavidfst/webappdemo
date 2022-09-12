package com.webappdemo.www;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.webappdemo.www.entity.Roles;
import com.webappdemo.www.service.RoleService;
import com.webappdemo.www.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Rollback(value = false)
@ActiveProfiles("test")
public class RoleServiceTest {

	@Autowired RoleService roleService;

	@Autowired UserService userService;

	@Test public void testFillRoles() {

		userService.saveRole(Roles.Normal.name(), Roles.Normal.DESCRIPTION);
		Assertions.assertThat(roleService.findAll()).size().isGreaterThan(0);
	}

	@Test public void shouldDeleteAllRoles() {

		roleService.deleteAll();
		Assertions.assertThat(roleService.findAll()).size().isLessThan(1);
	}
}
