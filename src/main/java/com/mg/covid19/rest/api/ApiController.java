package com.mg.covid19.rest.api;

import com.mg.covid19.model.entity.Country;
import com.mg.covid19.model.object.CountryRequestObj;
import com.mg.covid19.model.object.CountryResponseObj;
import com.mg.covid19.service.implementation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private Environment env;
    @Autowired
    private Transformer transformer;
    @Autowired
    private CountryService countryService;


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", env.getProperty("covid19.x-rapidapi-host"));
        headers.set("x-rapidapi-key", env.getProperty("covid19.x-rapidapi-key"));
        return headers;
    }


    private HttpEntity<String> initEntity(){
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }


    @GetMapping("/countries/*")   // http://localhost:7000/covid19/api/countries/*
    public ResponseEntity<List<Map>> getListOfCountriesOriginal() {
        String url = env.getProperty("covid19.url") + "/help/countries?format=json";
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                System.out.println("Total: "+data.size());
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
            return new ResponseEntity<>((List<Map>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((List<Map>) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/countries")   // http://localhost:7000/covid19/api/countries
    public ResponseEntity<List<CountryResponseObj>> getListOfCountries22() throws Exception{
        String url = env.getProperty("covid19.url") + "/help/countries?format=json";
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                List<CountryRequestObj> result1 = transformer.transform0022(data);
                List<CountryResponseObj> result2 = new ArrayList<>();
                System.out.println("Total: "+data.size());
                System.out.println("Total res1: "+result1.size());
                /*result1.forEach(c -> {
                    try {
                        CountryResponseObj savedCountry = countryService.createTree(c);
                        result2.add(savedCountry);              //CountryObj instead of RESPONCE REQ OBJ
                    } catch (Exception e) {                     //toDo create service accept lists, sout 2of2003 insert
                        e.printStackTrace();
                    }
                });*/
                return new ResponseEntity<>(result2, HttpStatus.OK);
            }
            return new ResponseEntity<>((List<CountryResponseObj>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((List<CountryResponseObj>) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/countries2")   // http://localhost:7000/covid19/api/countries
    public ResponseEntity<List<Country>> getListOfCountries2() {
        String url = env.getProperty("covid19.url") + "/help/countries?format=json";
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                List<Country> result = transformer.transform002(data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>((List<Country>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((List<Country>) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*

    @GetMapping("/docs")
    public ResponseEntity<Object> getAPIDocumentation() {
        String url = env.getProperty("covid19.url") + "/docs.json";
        ResponseEntity<Object> respEntity = restTemplate.exchange(url, HttpMethod.GET, initEntity(), Object.class);
        Object response = respEntity.getBody();
        if (response != null) {
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: data not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/total")   // http://localhost:8000/covid19/total
    public ResponseEntity<Map> getLatestTotals() {
        String url = env.getProperty("covid19.url") + "/totals?format=json";
        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), Map[].class);
        if (response != null && response.getStatusCode().value() == 200) {
            Map data = response.getBody()[0];   //because of API implementation, return single object in array
            if (!data.isEmpty()) {
                Map result = responseTransformer.transform003(data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>((Map) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Map) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/total/{date}")    // http://localhost:8000/covid19/total/2020-04-01
    public ResponseEntity<Map> getDailyReportTotals(@PathVariable String date) {
        String url = env.getProperty("covid19.url") + "/report/totals?date-format=YYYY-MM-DD&format=json&date="+date;
        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), Map[].class);
        if (response != null && response.getStatusCode().value() == 200) {
            Map data = response.getBody()[0];   //because of API implementation, return single object in array
            if (!data.isEmpty()) {
                Map result = responseTransformer.transform004(data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>((Map) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Map) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/country/{country}/{date}")    // http://localhost:8000/covid19/country/france/2020-04-03
    public ResponseEntity<Map> getDailyReportByCountryName(@PathVariable String country, @PathVariable String date) {
        String url = env.getProperty("covid19.url") + "/report/country/name?date-format=YYYY-MM-DD&format=json&date=" + date + "&name=" + country;
        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), Map[].class);
        if (response != null && response.getStatusCode().value() == 200) {
            Map data = response.getBody()[0];   //because of API implementation, return single object in array
            if (!data.isEmpty()) {
                Map result = responseTransformer.transform001(data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>((Map) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Map) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/country/{code}")    //
    public ResponseEntity<Map> getLatestCountryDataByCode(@PathVariable String code) {
        String url = env.getProperty("covid19.url") + "/country/code?format=json&code="+code;
        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, initEntity(), Map[].class);
        if (response != null && response.getStatusCode().value() == 200) {
            Map data = response.getBody()[0];   //because of API implementation, return single object in array
            if (!data.isEmpty()) {
                Map result = responseTransformer.transform001(data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>((Map) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Map) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


*/



































}
