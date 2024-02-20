package com.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.dto.LoginDTO;
import com.app.entities.Booking;

public interface BookingService
{
	BookingDTO book(BookingDTO dto);
	BookingDTO cancelBooking(BookingDTO dto);
	ApiResponse deleteBookingByDate(LocalDate date);
	Page<Booking> GetAllBooking(int pagenumber,int pagesize);



}
