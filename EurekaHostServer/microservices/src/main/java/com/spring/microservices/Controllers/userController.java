package com.spring.microservices.Controllers;

import com.spring.microservices.Entity.Rating;
import com.spring.microservices.Entity.User;
import com.spring.microservices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class userController {

    @Autowired
    UserService userService;

   //RestTemplate object used for communicating over http
    RestTemplate restTemplate;
    userController(UserService userService,RestTemplate restTemplate){

        this.userService=userService;
        this.restTemplate=restTemplate;
    }

    @GetMapping("getAllUser")
    public List<User> getAllUser(){
        List<User> theUsers = new ArrayList<>();
        theUsers=this.userService.getAllUser();
        theUsers.forEach(user -> {
            int id = user.getId();
            ArrayList<Rating> ratings = this.restTemplate.getForObject("http://localhost:9092/api/getRatingByUser/"+id,ArrayList.class);
            user.setRatings(ratings);

            System.out.println(ratings);
        });
       // return this.userService.getAllUser();
        return theUsers;
    }
    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable int id){
        ArrayList<Rating> ratings = this.restTemplate.getForObject("http://localhost:9092/api/getRatingByUser/"+id,ArrayList.class);

        User theUser = this.userService.getUser(id);
        theUser.setRatings(ratings);
        return theUser;
    }
    @PostMapping("createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User theUser =this.userService.createUser(user);
        return new ResponseEntity<>(theUser, HttpStatus.CREATED);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user){
        User theUser = this.userService.updateUser(id,user);
        return new ResponseEntity<>(theUser,HttpStatus.OK);
    }
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id){
         this.userService.deleteUser(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
