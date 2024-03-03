package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddReviewDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private int reviewId;
	
	private long id;
	private String review;
	
	

}
