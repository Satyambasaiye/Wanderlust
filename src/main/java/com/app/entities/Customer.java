package com.app.entities;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//
//user_id, name, email,phone,pass
@Entity
@Table(name="Customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity{
	
	@OneToMany(mappedBy ="customer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings=new ArrayList<Booking>();
	

	public void bookPackage(Booking b)
	{
		bookings.add(b);
		b.setCustomer(this);
	}
	
	public void cancelBooking(Booking b)
	{
		bookings.remove(b);
		b.setCustomer(null);
	}}
