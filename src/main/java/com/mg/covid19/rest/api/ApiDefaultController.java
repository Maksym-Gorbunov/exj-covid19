package com.mg.covid19.rest.api;

import com.mg.covid19.rest.RestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/docs/*")   // http://localhost:7000/covid19/api/docs/*
    public ResponseEntity<List<Map>> OpenAPIDocumentation() {
        //String url = env.getProperty("covid19.url") + "/docs.json";
        System.out.println(6666);
        String url = "https://covid-19-data.p.rapidapi.com/docs.json";
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


    @GetMapping("/total/*")   // http://localhost:7000/covid19/api/total/*
    public ResponseEntity<List<Map>> getLatestTotals() {
        String url = env.getProperty("covid19.url") + "/totals?format=json";
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


    @GetMapping("/total/date/{date}/*")   // http://localhost:7000/covid19/api/total/date/2020-04-01*
    public ResponseEntity<List<Map>> getDailyReportTotals(@PathVariable String date) {
        String url = env.getProperty("covid19.url") + "/report/totals?date-format=YYYY-MM-DD&format=json&date="+date;
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


    @GetMapping("/countries/date/{date}/name/{name}/*")   // http://localhost:7000/covid19/api/counties/date/2020-04-01/name/italy/*
    public ResponseEntity<List<Map>> getDailyReportByCountryName(@PathVariable String date, @PathVariable String name) {
        String url = env.getProperty("covid19.url") + "/report/totals?date-format=YYYY-MM-DD&format=json&date="+date+"&name="+name;
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


    @GetMapping("/countries/code/{code}/*")   // http://localhost:7000/covid19/api/countries/code/it/*
    public ResponseEntity<List<Map>> getLatestCountryDataByCode(@PathVariable String code) {
        String url = env.getProperty("covid19.url") + "/country/code?format=json&code="+code;
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


    @GetMapping("/countries/name/{name}/*")   // http://localhost:7000/covid19/api/countries/name/italy/*
    public ResponseEntity<List<Map>> getLatestCountryDataByName(@PathVariable String name) {
        String url = env.getProperty("covid19.url") + "/country?format=json&name="+name;
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


    @GetMapping("/countries/date/{date}/code/{code}/*")   // http://localhost:7000/covid19/api/countries/date/2020-04-01/code/it/*
    public ResponseEntity<List<Map>> getDailyReportByCountryCode(@PathVariable String date, @PathVariable String code) {
        String url = env.getProperty("covid19.url") + "/report/country/code?format=json&date-format=YYYY-MM-DD&date="+date+"&code="+code;
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
