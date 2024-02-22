package com.spring.microservicesHotel.ServiceImpl;

import com.spring.microservicesHotel.Entity.Hotel;
import com.spring.microservicesHotel.ExceptionHandler.ResourceNotFoundException;
import com.spring.microservicesHotel.Repository.HotelRepository;
import com.spring.microservicesHotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    HotelRepository hotelRepository;
    @Autowired
    HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }
    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel theHotel = new Hotel();
        theHotel.setName(hotel.getName());
        theHotel.setLocation(hotel.getLocation());
        theHotel.setMessage(hotel.getMessage());
        this.hotelRepository.save(theHotel);
        return theHotel;
    }

    @Override
    public Hotel findHotelById(int id) {
        Hotel hotel = this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is not found with id "+id));
        return hotel;
    }

    @Override
    public List<Hotel> findHotel() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels;
    }

    @Override
    public Hotel updateHotel(int id, Hotel hotel) {
        Hotel theHotel = this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is not found with id "+id));
        if(theHotel!=null){
            theHotel.setName(hotel.getName());
            theHotel.setLocation(hotel.getLocation());
            theHotel.setMessage(hotel.getMessage());
            this.hotelRepository.save(theHotel);
        }
        return theHotel;
    }

    @Override
    public void deleteHotel(int id) {
        Hotel theHotel = this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is not found with id "+id));
        if(theHotel!=null){
            this.hotelRepository.delete(theHotel);
        }
    }
}
