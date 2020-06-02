package com.mg.covid19.service;

import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.object.CountryRequestObj;
import com.mg.covid19.model.object.CountryResponseObj;

import java.util.List;

public interface ICountryService {

    List<CountryModel> getAll() throws Exception;

    List<CountryResponseObj> getAllTree() throws Exception;

    CountryModel get(long id) throws Exception;

    CountryModel create(CountryModel countryModel) throws Exception;

    CountryResponseObj createTree(CountryRequestObj countryRequestObj) throws Exception;

    CountryModel update(CountryModel countryModel) throws Exception;

    String delete (Long id) throws Exception;
    
}
