package com.altran.rh.exercicio.ecommerce.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@Length(min = 24, max = 24)
	private String id;

	@NotEmpty
	@Length(max = 120)
	private String name;

	@Indexed(unique = true)
	@NotEmpty
	@Length(max = 120)
	@Email
	private String email;

}