package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {

    @Transactional
    @Query("SELECT r FROM Review r JOIN FETCH r.customer")
    List<Review> findAllWithCustomer();

}
