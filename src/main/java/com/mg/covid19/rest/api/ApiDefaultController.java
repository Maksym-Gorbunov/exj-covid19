package com.mg.covid19.rest.api;

import com.mg.covid19.rest.RestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiDefaultController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private Environment env;
    @Autowired
    private RestHelper restHelper;


    @GetMapping("/countries/*")   // http://localhost:7000/covid19/api/countries/*
    public ResponseEntity<List<Map>> getListOfCountries() {
        String url = env.getProperty("covid19.url") + "/help/countries?format=json";
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, restHelper.initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
            return new ResponseEntity<>((List<Map>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((List<Map>) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
