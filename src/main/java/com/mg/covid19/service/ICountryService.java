package com.mg.covid19.service;

import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.object.CountryObj;

import java.util.List;

public interface ICountryService {

    List<CountryModel> getAll() throws Exception;

    List<CountryObj> getAllTree() throws Exception;

    CountryModel get(long id) throws Exception;

    CountryModel create(CountryModel countryModel) throws Exception;

    CountryObj createTree(CountryObj countryObj) throws Exception;
    
    List<CountryObj> createTrees(List<CountryObj> countriesObj) throws Exception;

    CountryModel update(CountryModel countryModel) throws Exception;

    CountryObj updateTree(CountryObj countryObj) throws Exception;

    String delete (Long id) throws Exception;
    
}
