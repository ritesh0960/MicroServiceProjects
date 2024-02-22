package com.spring.microservices.Service;

import com.spring.microservices.Entity.User;

import java.util.List;

public interface UserService {
    public User getUser(int id);
    public List<User> getAllUser();

    public User createUser(User user);
    public User updateUser(int id,User user);
    public void deleteUser(int id);
}
