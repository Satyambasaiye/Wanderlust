package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
@Entity
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	@Column(length = 150,nullable = false)
	private String review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="customer_id",nullable = false)
	private Customer customer;

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", review=" + review + ", customer=" + customer.getFname() + "]";
	}

	
}
