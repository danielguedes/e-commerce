package com.altran.rh.exercicio.ecommerce.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Item {

	@Id
	@Length(min = 24, max = 24)
	private String id;

	@NotNull
	@Max(120)
	private String name;

	@NotNull
	private Float value;

}