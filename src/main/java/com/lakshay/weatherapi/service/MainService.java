package com.lakshay.weatherapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshay.weatherapi.entity.GeocodedData;
import com.lakshay.weatherapi.entity.RequestsLogging;
import com.lakshay.weatherapi.model.Request;
import com.lakshay.weatherapi.model.Response;
import com.lakshay.weatherapi.repository.RequestLoggingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MainService {
    @Autowired
    private RequestLoggingRepository loggingRepository;
    @Autowired
    private GeocodingService geocodingService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ObjectMapper mapper;

    public Response mainService(Request request) {
        RequestsLogging requestLoggingObject = RequestsLogging.builder().pinCode(request.getPincode()).date(request.getFor_date()).build();
        loggingRepository.save(requestLoggingObject);
        log.info("Request saved " + requestLoggingObject);
        List<GeocodedData> location = geocodingService.geocodeThisPincode(request.getPincode());
        if (location == null || location.isEmpty()) {
            return Response.builder().message("No location identified for this pincode.").build();
        }
        log.info(location);
        return weatherService.getWeather(location.get(0), request.getFor_date());
    }
}
