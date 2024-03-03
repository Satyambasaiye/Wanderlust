package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entities.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDTO {
	private int reviewId;
  
    private String review;
    
    
	@NotBlank
    private String fname;

	@NotBlank
    private String lname;

	public ReviewDTO(int reviewId, String review, @NotBlank String fname, @NotBlank String lname) {
		super();
		this.reviewId = reviewId;
		this.review = review;
		this.fname = fname;
		this.lname = lname;
	}

	

	
	
	
	
	
	
}