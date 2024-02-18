package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Package {
	@Id
	private String packageId;
	@Column(length = 20,nullable = false)
	private String title;
	
	@Enumerated(EnumType.STRING)
	private Categories category;
	
	private int duration;
	
	@Column(nullable = false)
	private double cost;
	@Column( nullable = false)
	private String description;
	
	

	

}
