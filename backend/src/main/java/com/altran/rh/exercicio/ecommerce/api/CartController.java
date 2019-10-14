package com.altran.rh.exercicio.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.rh.exercicio.ecommerce.model.Cart;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.service.CartService;

@Controller
@RequestMapping(path = "/api/v1/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping()
	public @ResponseBody Cart update(@RequestBody Cart cart) {
		User authUser = new User();
		return cartService.updateByUser(authUser, cart);
	}

//	// TODO Remover
//	@GetMapping()
//	public @ResponseBody List<Cart> getAllItems() {
//		return cartService.getAllItems();
//	}
//
//	// TODO Remover
//	@GetMapping("/{id}")
//	public @ResponseBody Cart getById(@PathVariable String id) {
//		return cartService.getById(id);
//	}

}