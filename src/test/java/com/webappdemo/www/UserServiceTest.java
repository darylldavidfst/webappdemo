package com.webappdemo.www;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.webappdemo.www.entity.Roles;
import com.webappdemo.www.entity.User;
import com.webappdemo.www.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Rollback(value = false)
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired UserService userService;

	@Autowired UserDetailsService userDetailsService;

	@Test public void shouldSaveUser() {
		String username = "superuser@gmail.com";

		User root = new User("superuser@gmail.com", "superuser@gmail.com");

		userService.saveUser(root);
		userService.addRoleToUser(username, Roles.Normal.name());

		User user = userService.findByUsername(username);

		Assertions.assertThat(username).isEqualTo(user.getUsername());
	}

	@Test public void shouldDeleteAllUsers() {
		userService.deleteAll();
		Assertions.assertThat(userService.findAll()).size().isLessThan(1);
	}
}
