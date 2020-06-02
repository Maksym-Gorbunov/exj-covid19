package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.response.ProvinceResponce;

public interface IProvinceService {

    Iterable<ProvinceModel> getAll() throws Exception;

    ProvinceModel get(long id) throws Exception;

    ProvinceModel create(ProvinceModel model) throws Exception;

    ProvinceModel update(ProvinceModel model) throws Exception;

    String delete (Long id) throws Exception;


}
