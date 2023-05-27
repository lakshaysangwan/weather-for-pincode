package com.lakshay.weatherapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshay.weatherapi.entity.GeocodedData;
import com.lakshay.weatherapi.entity.UniqueWeatherRecord;
import com.lakshay.weatherapi.model.Response;
import com.lakshay.weatherapi.repository.UniqueWeatherRecordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Log4j2
public class WeatherService {
    @Value("${open-weather.key}")
    private String apiKey;
    @Autowired
    private UniqueWeatherRecordRepository repository;
    @Autowired
    private ObjectMapper mapper;

    public Response getWeather(GeocodedData location, String date) {
        List<UniqueWeatherRecord> records = repository.findByPincodeAndDate(location.getPinCode(), date);
        if (records.isEmpty()) {
            log.info("No result for location " + location + " and date " + date);
            RestTemplate template = new RestTemplate();
            RequestEntity<String> entity;
            ResponseEntity<String> response;
            LocalDate localDate = LocalDate.parse(date);
            LocalDateTime dateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
            long unixTimestamp = dateTime.toEpochSecond(ZoneOffset.UTC);
            try {
                entity = new RequestEntity<>(HttpMethod.GET, new URI("https://api.openweathermap.org/data/3.0/onecall/timemachine?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&dt=" + unixTimestamp + "&appid=" + apiKey));
                response = template.exchange(entity, String.class);
            } catch (URISyntaxException exception) {
                log.info("Geocoding request to opencage api failed");
                return null;
            }
            UniqueWeatherRecord record = UniqueWeatherRecord.builder().apiResponse(response.getBody()).pincode(location.getPinCode()).date(date).build();
            repository.save(record);
            log.info(record);
            return Response.builder().message("Success").weather_data(record.getApiResponse()).build();
        } else {
            log.info("Request already present for location " + location.getPinCode() + " and date " + date);
            return Response.builder().message("Success").weather_data(records.get(0).getApiResponse()).build();
        }
    }
}
