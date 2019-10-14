package com.altran.rh.exercicio.ecommerce.test.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.model.Item;

@RunWith(SpringRunner.class)
public class ItemTest extends AbstractModelTest<Item> {

	@Override
	@SuppressWarnings("unchecked")
	protected Item getBeanInstance() {
		return new Item();
	}
}
