package com.emprestimo.errors;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public NotFoundException(String message) {
        super(message);
    }
}
