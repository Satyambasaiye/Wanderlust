package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "packages")
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
	
	@OneToMany(mappedBy ="Package",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings=new ArrayList<Booking>();
	
	public void bookPackage(Booking b)
	{
		bookings.add(b);
		b.setPackage(this);
	}
	
	public void cancelBooking(Booking b)
	{
		bookings.remove(b);
		b.setPackage(null);
	}
	
	

	

}
