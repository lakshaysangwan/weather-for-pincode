package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.GeocodedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeocodingRepository extends CrudRepository<GeocodedData, Long> {
    List<GeocodedData> findByPinCode(int pinCode);
}
