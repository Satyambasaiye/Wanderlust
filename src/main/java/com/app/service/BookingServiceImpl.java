package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.BookingDao;
import com.app.dao.CustomerDao;
import com.app.dao.PackageDao;
import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.entities.Booking;
import com.app.entities.Customer;
import com.app.entities.Package;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingDao bookingRepo;
	
	@Autowired
	private PackageDao packageRepo;
	
	@Autowired
	private CustomerDao customerRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public BookingDTO book(BookingDTO dto) {
		Booking b=mapper.map(dto, Booking.class);

		Package pkg = packageRepo.findById(dto.getPackageId())
				.orElseThrow(()->new ResourceNotFoundException("invalid package "));
		pkg.bookPackage(b);
		
		Customer cust=customerRepo.findById(dto.getId())
				.orElseThrow(()->new ResourceNotFoundException("invalid customer "));
		cust.bookPackage(b);
	
		Booking savedEntity = bookingRepo.save(b);
		return mapper.map(savedEntity, BookingDTO.class);
	}

	@Override
	public BookingDTO cancelBooking(BookingDTO dto) {
		Booking b=mapper.map(dto, Booking.class);
		bookingRepo.delete(b);
		return null;
	}

	@Override
	public ApiResponse deleteBookingByDate(LocalDate date)
	{
		int i=bookingRepo.deleteBookingBeforeDate(date);
		System.out.println("in delete value "+i);
		return new ApiResponse("deleted succefully");
	}

	@Override
	public Page<Booking> GetAllBooking(int pagenumber, int pagesize) {
		Pageable pagable=PageRequest.of(pagenumber, pagesize);
		Page<Booking> bookingList = bookingRepo.findAll(pagable);
		System.out.println("in getall booking service!!!");
//		bookingList.forEach((s)->System.out.println(s.getPackage().getPackageId()));
		return bookingList;
	}

}
