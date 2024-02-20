package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddReviewDto;
import com.app.dto.CustomerDTO;
import com.app.dto.PackageDto;
import com.app.dto.ReviewDTO;
import com.app.dto.UpdateReview;
import com.app.service.ReviewService;

@RestController
@RequestMapping("/Reviews")
@CrossOrigin(origins="http://localhost:3000")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping
	public ResponseEntity<?> getAllcustomer(@RequestParam(defaultValue = "0",required = false)int pagenumber,
			@RequestParam (defaultValue = "3",required = false)int pageSize)
	{
		List<ReviewDTO> list = reviewService.getAllReview(pagenumber, pageSize);
		if(list.isEmpty()) {
			System.out.println("list is empty!!!!!!!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(list);
	}
	@PostMapping
	public ResponseEntity<?> addNewReview(@RequestBody @Valid AddReviewDto dto){
		System.out.println("in add rev controller");
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addNewReview(dto));
	}
	
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?>deleteReview(@PathVariable int reviewId){
		System.out.println("in delete rev controller "+reviewId);
		return ResponseEntity.ok(reviewService.deleteReview(reviewId));
	}
	
	@PutMapping("/{reviewId}")
	public ResponseEntity<?>updateReview(@PathVariable int reviewId,@RequestBody @Valid UpdateReview dto){
		System.out.println("in update rev controller "+reviewId+" "+dto);
		return ResponseEntity.ok(reviewService.updateReview(reviewId, dto));
	}
	
	
}
