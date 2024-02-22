package com.spring.microservicesHotel.Controller;

import com.spring.microservicesHotel.Entity.Hotel;
import com.spring.microservicesHotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class hotelController {
    HotelService hotelService;
    @Autowired
    hotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }

    @GetMapping("getAllHotel")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotels = this.hotelService.findHotel();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }
    @GetMapping("getHotel/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable int id){
        return new ResponseEntity<>(this.hotelService.findHotelById(id),HttpStatus.OK);
    }

    @PostMapping("createHotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(this.hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @PutMapping("updateHotel/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int id,@RequestBody Hotel hotel){
        return new ResponseEntity<>(this.hotelService.updateHotel(id,hotel),HttpStatus.OK);
    }

    @DeleteMapping("deleteHotel/{id}")
    public void deleteHotel(@PathVariable int id){
        this.hotelService.deleteHotel(id);
    }

}
