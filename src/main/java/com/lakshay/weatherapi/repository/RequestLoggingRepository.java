package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.RequestsLogging;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLoggingRepository extends MongoRepository<RequestsLogging, String> {
} 
