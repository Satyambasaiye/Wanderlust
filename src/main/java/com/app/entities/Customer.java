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
	
	
	
	@OneToMany(mappedBy ="customer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review> reviews=new ArrayList<Review>();
	
	public void addReview(Review r)
	{
		reviews.add(r);
		r.setCustomer(this);
	}
	
	public void deleteReview(Review r)
	{
		reviews.remove(r);
		r.setCustomer(null);
	}
	

	public void bookPackage(Booking b)
	{
		bookings.add(b);
		b.setCustomer(this);
	}
	
	public void cancelBooking(Booking b)
	{
		bookings.remove(b);
		b.setCustomer(null);
	}

	@Override
	public String toString() {
		return "Customer [getId()=" + getId() + ", getFname()=" + getFname() + ", getLname()=" + getLname()
				+ ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() + "]";
	}}
