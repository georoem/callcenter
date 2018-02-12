package com.almundo.callcenter.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.almundo.callcenter.common.MockTestData;
import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Test
	public void getAvailableUserWhenAnOperatorIsAvailableTest() {
		List<User> availableUsers = MockTestData.getOneOperatorAvailable();
		UserService userService = new UserService(availableUsers);
		User userAvailable = userService.getAvailableUser();
		assertEquals(Rol.OPERATOR, userAvailable.getRol().getId().longValue());
	}
	
	@Test
	public void getAvailableUserWhenOnlyASupervisorIsAvailableTest() {
		List<User> availableUsers = MockTestData.getOneOperatorBusyAndASupervisdorAvailable();
		UserService userService = new UserService(availableUsers);
		User userAvailable = userService.getAvailableUser();
		assertEquals(Rol.SUPERVISOR, userAvailable.getRol().getId().longValue());
	}

	@Test
	public void getAvailableUserWhenOnlyADirectorIsAvailableTest() {
		List<User> availableUsers = MockTestData.getOneOperatorAnSupervisorBusyAndADirectorAvailable();
		UserService userService = new UserService(availableUsers);
		User userAvailable = userService.getAvailableUser();
		assertEquals(Rol.DIRECTOR, userAvailable.getRol().getId().longValue());
	}
}
