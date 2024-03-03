package com.app.service;

import java.util.List;

import com.app.dto.AddReviewDto;
import com.app.dto.ApiResponse;
import com.app.dto.ReviewDTO;
import com.app.dto.UpdateReview;


public interface ReviewService {
	List<ReviewDTO> getAllReview(int pageNumber,int pageSize);
	
	ReviewDTO addNewReview(AddReviewDto dto);
	
	ApiResponse deleteReview(int reviewId);

	AddReviewDto updateReview(int reviewId,AddReviewDto dto);

	UpdateReview updateReview(int reviewId, UpdateReview dto);

	


}
