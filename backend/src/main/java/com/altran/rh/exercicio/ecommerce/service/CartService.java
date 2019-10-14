package com.altran.rh.exercicio.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.BusinessException;
import com.altran.rh.exercicio.ecommerce.model.Cart;
import com.altran.rh.exercicio.ecommerce.model.CartItem;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

//	// TODO Remover (apenas para desenv)
//	public Cart getById(String id) {
//		return cartRepository.findById(id).orElseThrow(() -> new BusinessException());
//	}
//
//	// TODO Remover (apenas para desenv)
//	public List<Cart> getAllItems() {
//		return cartRepository.findAll();
//	}

//	public static void validateCartItem(CartItem item) {
//		
//	}
//	
//	public Mono<Cart> update(Cart webCart) {
//		Mono<User> user = userService.getByEmail(getAuthenticatedUsername());
//		return user.subscribe(u -> updateByUser(u, webCart));
//
////		return updateByUser(user, webCart);
//	}

//	private Object getAuthenticatedUsername() {
//		return "daniel.guedes@gmail.com";
//	}

	public static void validateCartItem(CartItem cartItem) {
		if (cartItem.getQty() <= 0 || cartItem.getItem().getValue() <= 0) {
			throw new BadRequestException();
		}
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

	private void checkNoDuplicateItems(List<CartItem> cartItems) {
		Set<String> set2 = new HashSet<>();
		cartItems.forEach(i -> set2.add(i.getItem().getId()));
		if (set2.size() != cartItems.size()) {
			throw new BadRequestException();
		}
	}

//	public Mono<Cart> updateById(String id, Cart cart) {
//		// TODO Validar existencia
//		cart.setId(id);
//		return cartRepository.save(cart);
//	}
//
//	public Mono<Void> removeById(String id) {
//		return cartRepository.deleteById(id);
//	}
}
