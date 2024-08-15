package com.graph.graph.model;

import java.util.Base64;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID automatically generate hoga
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false) // Firstname empty nahi ho sakta
    private String firstname;

    @Column(nullable = false) // Lastname bhi empty nahi ho sakta
    private String lastname;

    @Column(unique = true, nullable = false) // Email unique aur non-nullable hona chahiye
    private String email;

    @Column(nullable = false) // Phone number empty nahi ho sakta
    private String phonenumber;

    @Column(nullable = false) // Password bhi empty nahi ho sakta
    private String password;

    // Getter aur Setter methods

    /**
     * @return id ki value return karega
     */
    public long getId() {
        return id;
    }

    /**
     * @param id ko set karega
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return firstname ki value return karega
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname ko set karega
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return lastname ki value return karega
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname ko set karega
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return email ki value return karega
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email ko set karega
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phonenumber ki value return karega
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber ko set karega
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return password ki value return karega
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password ko set karega
     */
    public void setPassword(String password) {
    	Base64.Encoder encoder = Base64.getEncoder();
		String normal = password;
		String encode = encoder.encodeToString(normal.getBytes());
		this.password = encode;
    }

    
}
