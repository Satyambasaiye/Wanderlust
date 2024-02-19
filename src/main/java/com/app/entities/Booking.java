package com.app.entities;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//child owning 
@Entity
@Table(name = "Booking")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private Long bookingID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID")
    private Package Package;

    @Column(name = "BookingDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    @Column(name = "ContactInfo",length = 100)
    private String contactInfo;
    

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}


