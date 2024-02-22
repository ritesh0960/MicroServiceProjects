package com.spring.microservices.Exceptions;

import com.spring.microservices.Payloads.Exception;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(){
        super("Resource is not found with given id");
    }
   public ResourceNotFoundException(String message){
        super(message);
    }

}
