package com.lakshay.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String message;
    @JsonRawValue
    private String weather_data;
}
