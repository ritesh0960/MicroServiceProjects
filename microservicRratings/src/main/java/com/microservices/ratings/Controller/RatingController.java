package com.microservices.ratings.Controller;

import com.microservices.ratings.Entity.Hotel;
import com.microservices.ratings.Entity.Rating;
import com.microservices.ratings.Service.RatingService;
import com.microservices.ratings.ServiceImpl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {
    RatingService ratingService;

    RestTemplate restTemplate;
    @Autowired
    RatingController(RatingService ratingService,RestTemplate restTemplate){

        this.ratingService=ratingService;
        this.restTemplate=restTemplate;
    }
    @GetMapping("getRating/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable int id){
        Rating rating = this.ratingService.getRating(id);
        int hotelId = rating.getHotel();
        Hotel hotel = this.restTemplate.getForObject("http://localhost:9091/api/getHotel/"+hotelId, Hotel.class);
        rating.setHotelDesc(hotel);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("getAllRating")
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> ratings = this.ratingService.getAllRating();
        ratings.forEach(rating -> {
            int id = rating.getHotel();
            Hotel hotel = this.restTemplate.getForObject("http://localhost:9091/api/getHotel/"+id,Hotel.class);
            rating.setHotelDesc(hotel);
        });
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }
    @PostMapping("createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return new ResponseEntity<>(this.ratingService.createRating(rating),HttpStatus.CREATED);
    }

    @PutMapping("updateRating/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable int id,@RequestBody Rating rating){
        return new ResponseEntity<>(this.ratingService.updateRating(id,rating),HttpStatus.OK);

    }
    @DeleteMapping("deleteRating/{id}")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable  int id){
        this.ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("getRatingByUser/{id}")
    public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable  int id){

        List<Rating> ratings = this.ratingService.getAllRatingByUserId(id);
        ratings.forEach(rating -> {
            int hotelId = rating.getHotel();
            Hotel hotel = this.restTemplate.getForObject("http://localhost:9091/api/getHotel/"+id,Hotel.class);
            rating.setHotelDesc(hotel);
        });

        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    @GetMapping("getRatingByHotel/{id}")
    public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable int id){
        List<Rating> ratings = this.ratingService.getAllRatingByHotelId(id);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

}
