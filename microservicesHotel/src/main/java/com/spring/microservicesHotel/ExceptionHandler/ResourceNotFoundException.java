package com.spring.microservicesHotel.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {
  public   ResourceNotFoundException(){
        super("Hotel is not available with the given id");
    }
  public   ResourceNotFoundException(String message){
        super(message);
    }
}
