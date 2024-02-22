package com.spring.microservices.ServiceImpl;

import com.spring.microservices.Entity.User;
import com.spring.microservices.Exceptions.ResourceNotFoundException;
import com.spring.microservices.Repository.UserRepository;
import com.spring.microservices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

     UserServiceImpl(UserRepository userRepository){
        userRepository = this.userRepository;
      }
    @Override
    public User getUser(int id) {
        /*Rating rating = new Rating(1,3,"Good");
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        return new User(1,"ritesh","raushan","123","6203",ratings); */
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User is not present with the id "+id));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
         User theUser = new User();
         theUser.setName(user.getName());
         theUser.setEmail(user.getEmail());
         theUser.setMobile(user.getMobile());
         theUser.setPassword(user.getPassword());
         userRepository.save(theUser);
         return theUser;
    }

    @Override
    public User updateUser(int id, User user) {
        User theUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User is not available with the id "+id));
        theUser.setName(user.getName());
        theUser.setEmail(user.getEmail());
        theUser.setMobile(user.getMobile());
        theUser.setPassword(user.getPassword());
        userRepository.save(theUser);
        return theUser;
    }

    @Override
    public void deleteUser(int id) {
         User theUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("user is not found with id "+id));
         if(theUser!=null){
             userRepository.delete(theUser);
         }

    }
}
