package com.lakshay.weatherapi.service;

import com.lakshay.weatherapi.entity.GeocodedData;
import com.lakshay.weatherapi.repository.GeocodingRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class GeocodingService {
    @Value("${open-cage.key}")
    private String apiKey;
    @Autowired
    private GeocodingRepository repository;

    public List<GeocodedData> geocodeThisPincode(int pincode) {
        List<GeocodedData> data = repository.findByPinCode(pincode);
        if (data.isEmpty()) {
            RestTemplate template = new RestTemplate();
            RequestEntity<String> entity;
            ResponseEntity<String> response;
            try {
                entity = new RequestEntity<>(HttpMethod.GET, new URI("https://api.opencagedata.com/geocode/v1/json?key=" + apiKey + "&q=" + pincode));
                response = template.exchange(entity, String.class);
            } catch (URISyntaxException exception) {
                log.info("Geocoding request to opencage api failed");
                return null;
            }
            JSONObject jsonResponse = new JSONObject(response.getBody());
            List<GeocodedData> geocodedDataList = new ArrayList<>();
            if (jsonResponse.getJSONObject("status").getInt("code") == 200 && jsonResponse.getInt("total_results") > 0) {
                log.info("API response is 200 and contains results");
                JSONArray results = jsonResponse.getJSONArray("results");
                log.info(results);
                for (int i = 0; i < results.length(); i++) {
                    JSONObject city = results.getJSONObject(i);
                    log.info(city);
                    if (city.getJSONObject("annotations").getInt("callingcode") == 91) {
                        String name, latitude, longitude;
                        name = city.getString("formatted");
                        latitude = String.valueOf(city.getJSONObject("geometry").getFloat("lat"));
                        longitude = String.valueOf(city.getJSONObject("geometry").getFloat("lng"));
                        GeocodedData geocodedData = GeocodedData.builder().pinCode(pincode).name(name).latitude(Float.parseFloat(latitude)).longitude(Float.parseFloat(longitude)).build();
                        repository.save(geocodedData);
                        geocodedDataList.add(geocodedData);
                    }
                }
                return geocodedDataList;
            } else {
                return null;
            }
        } else {
            return data;
        }
    }
}
