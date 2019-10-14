package com.altran.rh.exercicio.ecommerce.test.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.model.Cart;

@RunWith(SpringRunner.class)
public class CartTest extends AbstractModelTest<Cart> {

	@Override
	@SuppressWarnings("unchecked")
	protected Cart getBeanInstance() {
		return new Cart();
	}
}
