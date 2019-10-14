package com.altran.rh.exercicio.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Object already exists.")
public class ObjectExistsException extends BusinessException {
	private static final long serialVersionUID = 8321206950944359923L;
}
