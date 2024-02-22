package com.microservices.ratings.Repository;

import com.microservices.ratings.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findByUser(int id);
    List<Rating> findByHotel(int id);
}
