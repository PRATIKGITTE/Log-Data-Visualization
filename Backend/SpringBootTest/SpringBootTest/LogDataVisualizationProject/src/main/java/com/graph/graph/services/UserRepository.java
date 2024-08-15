package com.graph.graph.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graph.graph.model.User;

/*
 * Ye interface `UserRepository` hai jo `JpaRepository` se inherit karta hai.
 * `JpaRepository` ek Spring Data JPA ka part hai jo humare liye CRUD operations (Create, Read, Update, Delete) ko 
 * bahut easy banata hai. Isme basic CRUD operations ka implementation pehle se hi hota hai, to hume khud se likhne ki 
 * zarurat nahi padti.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * `Optional<User>` ek wrapper class hai jo possible null values ko handle karne
	 * me help karti hai. Is method `findByEmail` ka kaam hai database me email
	 * field se match karta hua user dhundhna. Agar email match karta hai to ye
	 * `User` object ko wrap karke return karega `Optional` ke andar, aur agar nahi
	 * milta to ye empty `Optional` return karega, jo null pointer exception se
	 * bachata hai.
	 */
	public Optional<User> findByEmail(String email);

//	 public void updatePassword(String email, String newPassword);
}
