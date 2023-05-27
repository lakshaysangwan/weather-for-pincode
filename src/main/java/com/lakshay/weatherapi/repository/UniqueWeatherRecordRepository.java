package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.UniqueWeatherRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniqueWeatherRecordRepository extends CrudRepository<UniqueWeatherRecord, Long> {
    @Query(value = "SELECT * FROM unique_weather_record WHERE pincode = ?1 AND date = ?2", nativeQuery = true)
    List<UniqueWeatherRecord> findByPincodeAndDate(int pincode, String date);
}
