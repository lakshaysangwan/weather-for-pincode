package com.lakshay.weatherapi.controller;

import com.lakshay.weatherapi.model.Request;
import com.lakshay.weatherapi.model.Response;
import com.lakshay.weatherapi.service.MainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {
    @Autowired
    private MainService service;

    @PostMapping("/weather")
    public Response mainRequest(@RequestBody Request request) {
        return service.mainService(request);
    }
}
