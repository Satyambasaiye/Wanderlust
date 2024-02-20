package com.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.entities.Booking;
import com.app.service.BookingService;

@RestController
@RequestMapping("/Booking")
@CrossOrigin(origins="http://localhost:3000")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	PropertyMap<BookingDTO, Booking>BookingMapping=new PropertyMap<BookingDTO, Booking>() {
		 protected void configure() {
			 map().getCustomer().setId(source.getId());
			 map().getPackage().setPackageId(source.getPackageId());
		 }
	};
	
	PropertyMap<Booking, BookingDTO>bookingToDto=new PropertyMap<Booking, BookingDTO>() {
	protected void configure() {
		map().setId(source.getCustomer().getId());
		map().setPackageId(source.getPackage().getPackageId());
	}
	};
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	public BookingController(ModelMapper mapper)
	{
		this.mapper=mapper;
		this.mapper.addMappings(BookingMapping);
		this.mapper.addMappings(bookingToDto);
	}
	@GetMapping
	public ResponseEntity<?>getAllBooking(@RequestParam(defaultValue = "0",required = false)int pagenumber,
			@RequestParam (defaultValue = "3",required = false)int pageSize)
	{
		Page<Booking> list = bookingService.GetAllBooking(pagenumber, pageSize);
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		List<BookingDTO> bookingLit = list.stream().map(booking->mapper.map(booking, BookingDTO.class))
		.collect(Collectors.toList());
		
		bookingLit.forEach(s->System.out.println(s));
		return ResponseEntity.ok(bookingLit);
	}
	
	@PostMapping
	public ResponseEntity<?>book(@RequestBody @Valid BookingDTO dto)
	{
		return ResponseEntity.ok(bookingService.book(dto));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?>cancelBooking(@RequestBody @Valid BookingDTO dto)
	{
		return ResponseEntity.ok(bookingService.cancelBooking(dto));
	}
	
    @DeleteMapping("/deleteBefore/{date}")
	public ResponseEntity<?>deleteBookingByDate(@PathVariable("date") LocalDate date)
	{
		return ResponseEntity.ok(bookingService.deleteBookingByDate(date));
	}
    
    
}
