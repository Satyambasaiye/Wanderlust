package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.app.entities.Customer;
import com.app.entities.Package;
import com.app.entities.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
	@JsonProperty(access = Access.READ_ONLY)
	
    private Long bookingID;
    
    private Long id;

	private String packageId;

    private LocalDate bookingDate;
    
    private String contactInfo;
    private PaymentStatus paymentStatus;





}
