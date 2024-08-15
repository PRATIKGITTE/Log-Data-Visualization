package com.graph.graph.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graph.graph.exception.ResourcesNotFoundException;
import com.graph.graph.model.User;
import com.graph.graph.service.impl.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // Frontend ke URL ko yahan se access mil raha hai
@RestController
@RequestMapping("/database") // Base URL mapping for the controller
public class UserController {

	@Autowired
	UserService us; // UserService ka instance inject ho raha hai

	@PostMapping("/register") // User registration ke liye POST request handle karega
	/*
	 * URL: http://localhost:8081/database/register Example JSON: { "firstname":
	 * "Fardeen", "lastname": "Khan", "email": "fardeen8303@gmail.com", "password":
	 * "Puranpur@123", "phonenumber": "7060455008" }
	 */
	public ResponseEntity<String> savingController(@Validated @RequestBody User u) {
		User resgis = us.saveingDetails(u); // User details ko save kar raha hai
		try {
			if (resgis == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Registration Failed"); // Agar save nahi hua
																									// toh response yeh
																									// hoga
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfull"); // Agar save ho gaya toh
																								// success response yeh
																								// hoga

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("DataBases, ServerError"); // Server error ka response
		}
	}

	@PostMapping("/login") // User login ke liye POST request handle karega
	/*
	 * URL: http://localhost:8081/database/login Example JSON: { "email":
	 * "fardeen8303@gmail.com", "password": "Puranpur@123" }
	 */
	public ResponseEntity<Boolean> userLogin(@Validated @RequestBody User u) {
		String email = u.getEmail(); // User ka email extract ho raha hai
		String password = u.getPassword(); // User ka password extract ho raha hai
		User modelUser;
		try {
			modelUser = us.getbyEmail(email).orElseThrow(() -> new ResourcesNotFoundException("Not Found")); // Email ke
																												// basis
																												// pe
																												// user
																												// ko
																												// database
																												// se
																												// fetch
																												// kar
																												// raha
																												// hai

			if (email.equals(modelUser.getEmail()) && password.equals(modelUser.getPassword())) { // Email aur password
																									// match kar raha
																									// hai toh login
																									// successful
				return ResponseEntity.ok(Boolean.TRUE);
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Boolean.FALSE); // Agar email ya password match
																							// nahi karta toh login fail

		} catch (ResourcesNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Agar user nahi mila ya koi error aaya
																				// toh bad request response
		}
	}

	@GetMapping("/forgot-password/{email}")
	public ResponseEntity<Map<String, Object>> forgotPassword(@PathVariable String email) {
	    Optional<User> userExists = us.getbyEmail(email);
	    Map<String, Object> response = new HashMap<>();
	    
	    if (userExists.isPresent()) { // Corrected the check for Optional
	        response.put("success", true);
	        response.put("message", "Email found, redirecting to Sign Up page.");
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("success", false);
	        response.put("message", "Email not found.");
	        return ResponseEntity.status(404).body(response);
	    }
	}

  

}
