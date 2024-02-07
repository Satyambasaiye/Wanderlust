package com.app.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor

public class User extends BaseEntity {
	
	@Enumerated(EnumType.STRING)	
	@Column(length = 15)
	private UserType type;
	@Column(name = "is_active" ,columnDefinition = "boolean default true")
	private boolean status;
//	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	    @MapsId
//	    private Address address;
	
	
	

}
