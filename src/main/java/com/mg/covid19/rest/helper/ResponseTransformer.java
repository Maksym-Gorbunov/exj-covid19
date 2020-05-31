package com.mg.covid19.rest.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseTransformer {


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


    public List<Country> transform002(List<Map> data) {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        for (Map map : data) {
            country.setName((String) map.get("name"));
            Location location = new Location(String.valueOf(map.get("latitude")), String.valueOf(map.get("longitude")));
            country.setLocation(location);
            Code code = new Code(String.valueOf(map.get("alpha2code")), String.valueOf(map.get("alpha3code")));
            country.setCode(code);
            countries.add(country);
        }
        if (!countries.isEmpty()) {
            return countries;
        }
        return null;
    }


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
