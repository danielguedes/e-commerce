package com.altran.rh.exercicio.ecommerce.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.service.UserService;

@Controller
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody User addUser(@Valid @RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping()
	public @ResponseBody List<User> getAllItems() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody User getById(@PathVariable String id) {
		return userService.getById(id);
	}

	@PutMapping("/{id}")
	public @ResponseBody User updateById(@PathVariable String id, @Valid @RequestBody User user) {
		return userService.updateById(id, user);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void removeById(@PathVariable String id) {
		userService.removeById(id);
	}

	@GetMapping("/notRegistered/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void validateInexistentUser(@PathVariable String email) {
		userService.validateInexistentUser(email);
	}

}