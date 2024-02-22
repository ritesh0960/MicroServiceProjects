package com.spring.microservicesHotel.Service;

import com.spring.microservicesHotel.Entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);
    public Hotel findHotelById(int id);
    public List<Hotel> findHotel();
    public Hotel updateHotel(int id, Hotel hotel);
    public void deleteHotel(int id);
}
