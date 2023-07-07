package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.UniqueWeatherRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniqueWeatherRecordRepository extends MongoRepository<UniqueWeatherRecord, String> {
    @Query("{'pincode': ?0, 'date': ?1}")
    List<UniqueWeatherRecord> findByPincodeAndDate(int pincode, String date);
}
