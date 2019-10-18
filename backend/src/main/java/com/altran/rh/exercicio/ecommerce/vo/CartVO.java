package com.altran.rh.exercicio.ecommerce.vo;

import java.util.List;

import com.altran.rh.exercicio.ecommerce.model.CartItem;
import com.altran.rh.exercicio.ecommerce.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartVO {

	private String id;

	private User user;

	private Integer totalItems;

	private Float totalValue;

	private List<CartItem> cartItems;

}
