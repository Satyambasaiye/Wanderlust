package com.app.dto;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDTO {

	@Email
    private String email;
    @JsonProperty(access = Access.READ_ONLY)
    private String password;


}
