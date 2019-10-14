package com.altran.rh.exercicio.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends BusinessException {
	private static final long serialVersionUID = 9099847066249793012L;
}
