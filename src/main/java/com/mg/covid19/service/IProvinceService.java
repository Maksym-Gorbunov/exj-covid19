package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.response.ProvinceResponce;

public interface IProvinceService {

    Iterable<ProvinceModel> getAll() throws Exception;

    Iterable<ProvinceResponce> getAllTree() throws Exception;

    ProvinceModel create(ProvinceModel model) throws Exception;

}
