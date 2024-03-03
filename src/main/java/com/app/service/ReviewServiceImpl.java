package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CustomerDao;
import com.app.dao.ReviewDao;
import com.app.dto.AddReviewDto;
import com.app.dto.ApiResponse;
import com.app.dto.PackageDto;
import com.app.dto.ReviewDTO;
import com.app.dto.UpdateReview;
import com.app.entities.Customer;
import com.app.entities.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao reviewrepo;
	@Autowired
	private ModelMapper mapper;
	

	@Autowired
	private CustomerDao customerRepo;
	

	@Override
	public List<ReviewDTO> getAllReview(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Review> reviewList = reviewrepo.findAll(pageable).getContent();
		if(reviewList.isEmpty())
			System.out.println("ghanta mila");
		

	
		  return reviewList.stream()
		            .map(rev -> new ReviewDTO(
		                    rev.getReviewId(),
		                    rev.getReview(),
		                    rev.getCustomer().getFname(),
		                    rev.getCustomer().getLname())
		            )
		            .collect(Collectors.toList());
	}

	@Override
	public ReviewDTO addNewReview(AddReviewDto dto) {
		Review revEntity=mapper.map(dto, Review.class);
		Customer customer = customerRepo.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("invalid customer details"));
		customer.addReview(revEntity);
		Review savedrev=reviewrepo.save(revEntity);
		return mapper.map(savedrev, ReviewDTO.class);
	}

	@Override
	public ApiResponse deleteReview(int reviewId) {
		Review rev=reviewrepo.findById(reviewId)
				.orElseThrow(()->new ResourceNotFoundException("invalid review id"));
		reviewrepo.delete(rev);
		// TODO Auto-generated method stub
		return new ApiResponse("Review with reviewId "+rev.getReviewId()+" deleted");
	}

	@Override
	public UpdateReview updateReview(int reviewId, UpdateReview dto) {
		
		Review rev=reviewrepo.findById(reviewId).
				orElseThrow(()->new ResourceNotFoundException("invalid review id"));
		
//		mapper.map(dto, rev);
		rev.setReview(dto.getReview());
		System.out.println("after mapping");
		
		return dto;
	}

	@Override
	public AddReviewDto updateReview(int reviewId, AddReviewDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
