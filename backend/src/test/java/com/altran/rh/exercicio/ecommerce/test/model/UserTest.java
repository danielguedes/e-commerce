package com.altran.rh.exercicio.ecommerce.test.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.model.User;

@RunWith(SpringRunner.class)
public class UserTest extends AbstractModelTest<User> {

	@Override
	@SuppressWarnings("unchecked")
	protected User getBeanInstance() {
		return new User();
	}
}
