package com.spring.microservices.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private int id;
    private int ratingNo;
    private String comments;
    private Hotel hotel;
}
