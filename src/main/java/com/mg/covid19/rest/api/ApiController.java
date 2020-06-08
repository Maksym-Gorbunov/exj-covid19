package com.mg.covid19.rest.api;

import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.object.CountryObj;
import com.mg.covid19.rest.RestHelper;
import com.mg.covid19.service.implementation.CountryService;
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
public class ApiController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private RestHelper restHelper;
    @Autowired
    private CountryService countryService;
    @Autowired
    private Environment env;
    @Autowired
    Mapper mapper;


    //populate database with countries, code, location
    @GetMapping("/populate/countries/v1")   //http://localhost:7000/covid19/api/populate/countries/v1
    public ResponseEntity<List<CountryObj>> populateCountries1() throws Exception {
        String url = restHelper.getUrl() + "/help/countries?format=json";
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, restHelper.initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                List<CountryObj> countriesObj = restHelper.transformData1(data);
                List<CountryObj> savedCountiesObj = countryService.createTrees(countriesObj);
                return new ResponseEntity<>(savedCountiesObj, HttpStatus.OK);
            }
            return new ResponseEntity<>((List<CountryObj>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((List<CountryObj>) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/statistic/name/{name}")   //http://localhost:7000/covid19/api/statistic/name/usa
    public ResponseEntity<CountryObj> getStatisticObjByCountryName(@PathVariable String name) throws Exception {
        String url = env.getProperty("covid19.url") + "/country?format=json&name="+name;
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, restHelper.initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                CountryObj countryObj = restHelper.transformData2(data.get(0));
                return new ResponseEntity<>(countryObj, HttpStatus.OK);
            }
            return new ResponseEntity<>((CountryObj) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((CountryObj) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Statistic getStatisticByCountryName(String name) throws Exception {
        String url = env.getProperty("covid19.url") + "/country?format=json&name="+name;
        ResponseEntity<List<Map>> response = restTemplate.exchange(url, HttpMethod.GET, restHelper.initEntity(), new ParameterizedTypeReference<List<Map>>() {
        });
        if (response != null && response.getStatusCode().value() == 200) {
            List<Map> data = response.getBody();
            if (!data.isEmpty()) {
                Statistic statistic = restHelper.transformData3(data.get(0));
                return statistic;
            }
        }
        return null;
    }


    //populate database with statistic
    @GetMapping("/populate/countries/v2")   //http://localhost:7000/covid19/api/populate/countries/v1
    public ResponseEntity<List<CountryObj>> populateCountries2() throws Exception {
        List<CountryObj> countries = countryService.getAllTree();
        if(countries==null){
            return new ResponseEntity<>((List<CountryObj>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        int i = 1;
        for(CountryObj countryObj : countries){
            Statistic statistic = getStatisticByCountryName(countryObj.getName());
            countryObj.setStatistic(mapper.toModel(statistic));
            System.out.println(i+" / "+countries.size() + " statistic inserted");
            countryService.updateTree(countryObj);
            Thread.sleep(2000);
            i++;
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


    //toDO continua to implement all methods below
    //toDo impl update methods, for refreshing entities which exist but not consist all fields

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
