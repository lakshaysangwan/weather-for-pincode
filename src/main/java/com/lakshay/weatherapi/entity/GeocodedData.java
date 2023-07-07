package com.lakshay.weatherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class GeocodedData {
    @Id
    private String id;
    private String name;
    private int pinCode;
    private float latitude;
    private float longitude;
}
