package com.mg.covid19.rest;

import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Country;
import com.mg.covid19.model.entity.Location;
import com.mg.covid19.model.object.CountryObj;
import com.mg.covid19.service.implementation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestHelper {

    @Autowired
    Mapper mapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private Environment env;

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", env.getProperty("covid19.x-rapidapi-host"));
        headers.set("x-rapidapi-key", env.getProperty("covid19.x-rapidapi-key"));
        return headers;
    }


    public HttpEntity<String> initEntity() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

    public String getUrl(){
        return env.getProperty("covid19.url");
    }

    public List<CountryObj> transformData1(List<Map> data) {
        List<CountryObj> countries = new ArrayList<>();
        for (Map map : data) {
            CountryObj country = new CountryObj();
            country.setName((String) map.get("name"));
            Location location = new Location();
            if(map.get("latitude")!=null){
                location.setLatitude((double) map.get("latitude"));
            }
            if(map.get("longitude")!=null){

                location.setLongitude((double) map.get("longitude"));
            }
            country.setLocation(mapper.toModel(location));
            Code code = new Code();
            code.setAlpha2code(String.valueOf(map.get("alpha2code")));
            code.setAlpha3code(String.valueOf(map.get("alpha3code")));
            country.setCode(mapper.toModel(code));
            countries.add(country);
        }
        if (!countries.isEmpty()) {
            return countries;
        }
        return null;
    }
    /*
    public Map transform001(Map data) {
        Map result = new HashMap();
        Country country = new Country();
        List<Province> provinces = new ArrayList<>();
        country.setName((String) data.get("country"));
        Location location = new Location(String.valueOf(data.get("latitude")), String.valueOf(data.get("longitude")));
        country.setLocation(location);
        List<Map> provincesMapList = (List<Map>) data.get("provinces");
        if (!provincesMapList.isEmpty()) {
            for (Map provinceMap : provincesMapList) {
                Province province = new Province();
                province.setName((String) provinceMap.get("province"));
                Statistic statistic = new Statistic();
                statistic.setActive((Integer) provinceMap.get("active"));
                statistic.setConfirmed((Integer) provinceMap.get("confirmed"));
                statistic.setDeaths((Integer) provinceMap.get("deaths"));
                statistic.setRecovered((Integer) provinceMap.get("recovered"));
                province.setStatistic(statistic);
                provinces.add(province);
            }
        }
        result.put("date", String.valueOf(data.get("date")));
        if (country != null && !provinces.isEmpty()) {
            country.setProvinces(provinces);
            result.put("country", country);
            return result;
        }
        return null;
    }

    */




    /*

    public Map transform003(Map data) {
        Map result = new HashMap();
        Statistic statistic = new Statistic();
        statistic.setActive((Integer) data.get("critical"));
        statistic.setRecovered((Integer) data.get("recovered"));
        statistic.setConfirmed((Integer) data.get("confirmed"));
        statistic.setDeaths((Integer) data.get("deaths"));
        result.put("statistic:", statistic);
        result.put("lastChange", String.valueOf(data.get("lastChange")));
        result.put("lastUpdate", String.valueOf(data.get("lastUpdate")));
        return result;
    }


    public Map transform004(Map data) {
        Map result = new HashMap();
        Statistic statistic = new Statistic();
        statistic.setActive((Integer) data.get("active"));
        statistic.setRecovered((Integer) data.get("recovered"));
        statistic.setConfirmed((Integer) data.get("confirmed"));
        statistic.setDeaths((Integer) data.get("deaths"));
        result.put("statistic", statistic);
        result.put("date", String.valueOf(data.get("date")));
        return result;
    }

*/

}
