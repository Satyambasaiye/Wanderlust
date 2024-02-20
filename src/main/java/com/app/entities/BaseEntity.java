package com.app.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20, nullable = false)
    private String fname;
    
    @Column(length = 20, nullable = false)
    private String lname;
    
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    
    @Column(length = 20, nullable = false)
    private String phone;
    
    @Column(length = 15, nullable = false)
    private String password;
    
    
    @Column(length = 30)
    private String street;
    
    @Column(length = 20)
    private String city;
    
    @Column(length = 20)
    private String state;
    
    @Column(length = 20)
    private String country;
        
    @Column(length = 20, name = "zip_code")
    private String zipCode;
    
    
    @Version
    private Long version;
}
