package com.altran.rh.exercicio.ecommerce.test.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.model.CartItem;

@RunWith(SpringRunner.class)
public class CartItemTest extends AbstractModelTest<CartItem> {

	@Override
	@SuppressWarnings("unchecked")
	protected CartItem getBeanInstance() {
		return new CartItem();
	}
}
