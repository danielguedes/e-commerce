package com.altran.rh.exercicio.ecommerce.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Cart {

	@Id
	@Length(min = 24, max = 24)
	private String id;

	@DBRef
	@NotNull
	private User user;

	@NotNull
	private List<CartItem> cartItems;

}