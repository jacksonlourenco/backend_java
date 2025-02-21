package com.api.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepition extends RuntimeException {
    
    public ResourceNotFoundExcepition(String mensagem){
        super(mensagem);
    }
}
