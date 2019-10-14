package com.altran.rh.exercicio.ecommerce.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {

	@NotNull
	@DBRef
	private Item item;

	@NotNull
	private Integer qty;

}
