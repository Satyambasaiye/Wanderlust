package com.app.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import com.app.entities.Categories;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PackageDto {
	private String packageId;
	private String title;
	
	private Categories category;
	
	private int duration;
	
	@Column(nullable = false)
	private double cost;
	@Column( nullable = false)
	private String description;
	

}
