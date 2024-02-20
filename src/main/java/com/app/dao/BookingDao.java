package com.app.dao;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Long>
{
	@Transactional
	@Modifying
	@Query("delete from Booking b where b.bookingDate<:date")
	int deleteBookingBeforeDate(LocalDate date);

}
