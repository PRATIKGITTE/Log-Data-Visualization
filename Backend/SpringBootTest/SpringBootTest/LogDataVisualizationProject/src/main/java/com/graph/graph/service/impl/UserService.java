package com.graph.graph.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graph.graph.model.User;
import com.graph.graph.services.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository ur; // UserRepository ka instance inject ho raha hai

    // User details ko save karne ke liye method
    public User saveingDetails(User u) {
        return ur.save(u); // User object ko database mein save karega
    }
	public Optional<User> getbyEmail(String email)
	{
		return ur.findByEmail(email);
	}
//    // Email ke through user ko retrieve karne ka method
//    public Optional<User> doesUserExistByEmail(String email) {
//        return ur.findByEmail(email); // Email ki basis pe user ko dhundega
//    }
    
    
   
    
}
