package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.GeocodedData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeocodingRepository extends MongoRepository<GeocodedData, String> {
    @Query("{pincode:'?0'}")
    List<GeocodedData> findByPinCode(int pinCode);
}
