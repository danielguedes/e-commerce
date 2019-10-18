package com.altran.rh.exercicio.ecommerce.test.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.altran.rh.exercicio.ecommerce.model.Cart;
import com.altran.rh.exercicio.ecommerce.model.CartItem;
import com.altran.rh.exercicio.ecommerce.model.Item;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.repository.CartRepository;
import com.altran.rh.exercicio.ecommerce.service.CartService;
import com.altran.rh.exercicio.ecommerce.vo.CartVO;

@RunWith(SpringRunner.class)
public class CartServiceTest {

	static final String DEFAULT_ITEM1_ID = "a59d3c03a9f34747e33aas53";

	@TestConfiguration
	static class UserServiceIntegrationTestContextConfiguratio {

		@Bean
		public CartService cartService() {
			return new CartService();
		}
	}

	@Autowired
	private CartService cartService;

	@MockBean
	private CartRepository cartRepository;

	Cart cart;
	User user;
	Item item1;
	Item item2;
	CartItem cartItem1;
	CartItem cartItem2;
	List<CartItem> cartItemList;

	@Before
	public void setUp() {
		cart = new Cart();
		user = new User();
		item1 = new Item();
		item2 = new Item();
		cartItem1 = new CartItem();
		cartItem2 = new CartItem();
		cartItemList = new ArrayList<>();

		user.setId("5d9d4c0359f14777e33ca253");
		user.setEmail("daniel.guedes@gmail.com");
		user.setName("Daniel Guedes");

		item1.setId("5d9dee4c59f14777e33ca277");
		item1.setName("Product Two");
		item1.setValue(new Float(230.92));

		item2.setId(DEFAULT_ITEM1_ID);
		item2.setName("Product One");
		item2.setValue(new Float(30.56));

		cartItem1.setItem(item1);
		cartItem1.setQty(3);

		cartItem2.setItem(item2);
		cartItem2.setQty(1);

		cartItemList.add(cartItem1);
		cartItemList.add(cartItem2);

		cart.setId(DEFAULT_ITEM1_ID);
		cart.setUser(user);
		cart.setCartItems(cartItemList);

		Mockito.when(cartRepository.findById(DEFAULT_ITEM1_ID)).thenReturn(Optional.of(cart));
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);
		Mockito.when(cartRepository.findByUserId(user.getId())).thenReturn(Optional.of(cart));
		Mockito.when(cartRepository.findByUserId("non-existent-userid")).thenReturn(Optional.empty());
	}

	@Test
	public void whenUpdateByExistentUserThenCartShouldBeFound() {
		cart.getCartItems().get(0).setQty(40);
		Cart found = cartService.updateByUser(user, cart);
		assertThat(found.getCartItems().get(0).getQty()).isEqualTo(40);
	}

	@Test
	public void whenUpdateByNonExistentUserThenCartShouldBeCreated() {
		User newUser = new User();
		newUser.setId("non-existent-userid");
		newUser.setName("User name");
		newUser.setEmail("email@test.com");
		cart.getCartItems().get(0).setQty(40);
		Cart found = cartService.updateByUser(newUser, cart);
		assertThat(found.getCartItems().get(0).getQty()).isEqualTo(40);
	}

	@Test(expected = BadRequestException.class)
	public void whenUpdateWithItemQtyLessThanOneThenExceptionShouldBeThrow() {
		cart.getCartItems().get(0).setQty(0);
		cartService.updateByUser(user, cart);
	}

	@Test(expected = BadRequestException.class)
	public void whenUpdateWithDupliacteItemsThenExceptionShouldBeThrow() {
		cart.getCartItems().set(1, cart.getCartItems().get(0));
		cartService.updateByUser(user, cart);
	}

	public void whenGetCartThenCartVOShouldBeReturned() {
		CartVO cartVO = cartService.getByUser(user);
		assertThat(cartVO.getCartItems().get(0).getItem().getName().equals(item2.getName()));
		assertThat(cartVO.getTotalItems() == 2);
		assertThat(cartVO.getTotalItems() == 2);
		assertThat(cartVO.getTotalValue() == 261.48f);
	}

}
