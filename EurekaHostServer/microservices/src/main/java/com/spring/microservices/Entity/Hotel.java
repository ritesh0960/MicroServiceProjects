package com.spring.microservices.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Hotel {
    private int id;
    private String name;
    private String location;
    private String message;
}
