package com.mg.covid19.service;

import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.object.CountryTree;

import java.util.List;

public interface ICountryService {

    List<CountryModel> getAll() throws Exception;

    List<CountryTree> getAllTree() throws Exception;

    CountryModel get(long id) throws Exception;

    CountryModel create(CountryModel model) throws Exception;

    CountryTree createTree(CountryTree countryTree) throws Exception;

    CountryModel update(CountryModel model) throws Exception;

    String delete (Long id) throws Exception;
    
}
