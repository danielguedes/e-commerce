package com.altran.rh.exercicio.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.NotFoundException;
import com.altran.rh.exercicio.ecommerce.model.Cart;
import com.altran.rh.exercicio.ecommerce.model.CartItem;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.repository.CartRepository;
import com.altran.rh.exercicio.ecommerce.vo.CartVO;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public CartVO getByUser(User user) {
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(NotFoundException::new);
		CartVO cartVO = new CartVO();
		new DozerBeanMapper().map(cart, cartVO);
		cartVO.setCartItems(cart.getCartItems().stream()
				.sorted((c1, c2) -> c1.getItem().getName().compareTo(c2.getItem().getName()))
				.collect(Collectors.toList()));
		cartVO.setTotalItems(cart.getCartItems().stream().mapToInt(CartItem::getQty).sum());
		cartVO.setTotalValue(cart.getCartItems().stream().map(i -> i.getItem().getValue()).reduce(Float::sum).get());
		return cartVO;
	}

	public Cart updateByUser(User user, Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		checkNoDuplicateItems(cartItems);
		cartItems.forEach(CartService::validateCartItem);
		String cartId = cartRepository.findByUserId(user.getId()).orElse(new Cart()).getId();
		cart.setId(cartId);
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	public static void validateCartItem(CartItem cartItem) {
		if (cartItem.getQty() <= 0 || cartItem.getItem().getValue() <= 0) {
			throw new BadRequestException();
		}
	}

	private void checkNoDuplicateItems(List<CartItem> cartItems) {
		Set<String> set2 = new HashSet<>();
		cartItems.forEach(i -> set2.add(i.getItem().getId()));
		if (set2.size() != cartItems.size()) {
			throw new BadRequestException();
		}
	}

}
