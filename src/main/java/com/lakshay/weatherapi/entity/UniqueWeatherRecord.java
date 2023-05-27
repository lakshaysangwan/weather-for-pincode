package com.lakshay.weatherapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniqueWeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int pincode;
    private String date;
    @Column(columnDefinition = "JSON")
    private String apiResponse;
}
