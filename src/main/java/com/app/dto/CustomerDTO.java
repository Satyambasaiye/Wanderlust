package com.app.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerDTO {
	@JsonProperty(access = Access.READ_ONLY)
    private Long id;
    
	@NotBlank
    private String fname;
    
	@NotBlank
    private String lname;
    
	@NotBlank
	@Email
    private String email;
    
	@NotBlank
    private String phone;
    
	@JsonProperty(access = Access.WRITE_ONLY) 
	@Length(max = 15,min = 8)
    private String password;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Length(max = 15,min = 8)

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
    
    

}
