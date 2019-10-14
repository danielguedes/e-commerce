package com.altran.rh.exercicio.ecommerce.test.model;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public abstract class AbstractModelTest<T> {

	protected String[] propertiesToBeIgnored;

	@Test
	public void getterAndSetterCorrectness() throws TestFailedException {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(getBeanInstance().getClass());
	}

	@SuppressWarnings("hiding")
	protected abstract <T> T getBeanInstance();
}