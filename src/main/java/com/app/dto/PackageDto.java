package com.app.dto;

import javax.persistence.Column;

import com.app.entities.Categories;

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
