package com.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.app.entities.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
	
	
	@JsonProperty(access = Access.READ_ONLY)
    private Long id;
    
	@NotBlank
    private String fname;
    
	@NotBlank
    private String lname;
    
	@NotBlank
    private String email;
    
	@NotBlank
    private String phone;
    
	@JsonProperty(access = Access.WRITE_ONLY) //required only in de-ser.
    private String password;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmPassword;
   
	@NotBlank
    private String street;
    
	@NotBlank
    private String city;
    
	@NotBlank
    private String state;
    
	@NotBlank
    private String country;
     
	@NotBlank
    private String zipCode;
	
	private UserType type;
	@JsonProperty(access = Access.READ_ONLY )
	
	private boolean status;
    
    

}
