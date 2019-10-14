package com.altran.rh.exercicio.ecommerce.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.BusinessException;
import com.altran.rh.exercicio.ecommerce.exception.ObjectExistsException;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.repository.UserRepository;
import com.altran.rh.exercicio.ecommerce.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	static final String DEFAULT_USER_ID = "5d9d4c0359f14777e33ca253";
	static final String DEFAULT_USER_EMAIL = "daniel.guedes@gmail.com";
	static final String DEFAULT_USER_NAME = "Daniel Guedes";

	@TestConfiguration
	static class UserServiceIntegrationTestContextConfiguratio {

		@Bean
		public UserService userService() {
			return new UserService();
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	User user;

	@Before
	public void setUp() {
		user = new User();
		user.setId(DEFAULT_USER_ID);
		user.setEmail(DEFAULT_USER_EMAIL);
		user.setName(DEFAULT_USER_NAME);

		List<User> userList = new ArrayList<>();
		userList.add(user);

		Mockito.when(userRepository.findById(DEFAULT_USER_ID)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findByEmail(DEFAULT_USER_EMAIL)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		UserRepository userRepository2 = Mockito.mock(UserRepository.class);
		doNothing().when(userRepository2).deleteById(DEFAULT_USER_ID);
	}

	@Test
	public void whenCreatedThenUserShouldBeFound() {
		String name = DEFAULT_USER_NAME;
		User found = userService.getById(DEFAULT_USER_ID);

		assertThat(found.getName()).isEqualTo(name);
	}

	@Test
	public void whenValidateInexistentUserThenShouldReturnOk() {
		userService.validateInexistentUser("non-existent@email.com");
	}

	@Test(expected = BadRequestException.class)
	public void whenValidateExistentUserThenExceptionShouldBeThrow() {
		userService.validateInexistentUser(DEFAULT_USER_EMAIL);
	}

	@Test
	public void whenValidIdThenUserShouldBeFound() {
		String name = DEFAULT_USER_NAME;
		User found = userService.getById(DEFAULT_USER_ID);
		assertThat(found.getName()).isEqualTo(name);
	}

	@Test(expected = BusinessException.class)
	public void whenInvalidIdThenExceptionShouldBeThrow() {
		userService.getById("any-inexistent-id");
	}

	@Test
	public void whenUpdateByIdThenUserShouldBeFound() {
		String name = "Test name";
		user.setName(name);
		User updated = userService.updateById(DEFAULT_USER_ID, user);
		assertThat(updated.getName()).isEqualTo(name);
	}

	@Test
	public void whenRemoveByIdThenUserShouldNotBeFound() {
		userService.removeById(DEFAULT_USER_ID);
	}

	@Test
	public void whenFindAllThenUserShouldBeFound() {
		assertThat(userService.getAll().get(0).getName()).isEqualTo(user.getName());
	}

	@Test
	public void whenCreateThenUserShouldBeFound() {
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
		assertThat(userService.create(user)).isEqualTo(user);
	}

	@Test(expected = ObjectExistsException.class)
	public void whenCreateExistentEmailThenObjectExistsShouldBeThrow() {
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		userService.create(user);
	}

}
