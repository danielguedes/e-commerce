package com.altran.rh.exercicio.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.rh.exercicio.ecommerce.model.Cart;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.service.CartService;
import com.altran.rh.exercicio.ecommerce.vo.CartVO;

@Controller
@RequestMapping(path = "/api/v1/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping()
	public @ResponseBody CartVO get() {
		User authUser = getAutehnticatedUser();
		return cartService.getByUser(authUser);
	}

	@PostMapping()
	public @ResponseBody Cart update(@RequestBody Cart cart) {
		User authUser = getAutehnticatedUser();
		return cartService.updateByUser(authUser, cart);
	}

	private User getAutehnticatedUser() {
		return new User();
	}
}