package com.microservices.ratings.ServiceImpl;

import com.microservices.ratings.Entity.Rating;
import com.microservices.ratings.Exception.ResourceNotFoundException;
import com.microservices.ratings.Repository.RatingRepository;
import com.microservices.ratings.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository ratingRepository;
    @Autowired
    RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }

    @Override
    public Rating getRating(int id) {
        Rating theRating = new Rating();
        theRating = this.ratingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("rating is not available with the id "+id));
        return theRating;
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating createRating(Rating rating) {
        this.ratingRepository.save(rating);
        return rating;
    }

    @Override
    public Rating updateRating(int id,Rating rating) {
        Rating theRating = this.ratingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Rating is not present with the id "+id));

        if(theRating!=null){
            theRating.setHotel(rating.getHotel());
            theRating.setUser(rating.getUser());
            theRating.setReview(rating.getReview());
            theRating.setRatingNumber(rating.getRatingNumber());
            this.ratingRepository.save(theRating);

        }
        return theRating;
    }

    @Override
    public void deleteRating(int id) {
        Rating theRating = this.ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating is not present with the given Id "+id));
        this.ratingRepository.delete(theRating);
    }

    @Override
    public List<Rating> getAllRatingByUserId(int id) {
        List<Rating> ratings = this.ratingRepository.findByUser(id);
        return ratings;
    }

    @Override
    public List<Rating> getAllRatingByHotelId(int id) {
        List<Rating> ratings = this.ratingRepository.findByHotel(id);
        return ratings;
    }
}
