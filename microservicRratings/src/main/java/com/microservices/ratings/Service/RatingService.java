package com.microservices.ratings.Service;

import com.microservices.ratings.Entity.Rating;

import java.util.List;

public interface RatingService {

    public Rating getRating(int id);

    public List<Rating> getAllRating();
    public Rating createRating(Rating rating);
    public Rating updateRating(int id,Rating rating);

    public void deleteRating(int id);
    public List<Rating> getAllRatingByUserId(int id);
    public List<Rating> getAllRatingByHotelId(int id);
}
