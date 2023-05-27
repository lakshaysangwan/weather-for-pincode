package com.lakshay.weatherapi.model;

import lombok.Data;

@Data
public class Request {
    private int pincode;
    private String for_date;
}
