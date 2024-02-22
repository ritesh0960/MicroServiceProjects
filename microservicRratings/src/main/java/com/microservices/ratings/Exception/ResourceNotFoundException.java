package com.microservices.ratings.Exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Rating is not available with the given id");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
