package com.lakshay.weatherapi.repository;

import com.lakshay.weatherapi.entity.RequestsLogging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface RequestLoggingRepository extends CrudRepository<RequestsLogging, Long> {
} 
