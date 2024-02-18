package com.app.entities;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
}
