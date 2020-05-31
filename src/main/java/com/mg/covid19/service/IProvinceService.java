package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;

public interface IProvinceService {

    Iterable<ProvinceModel> getAll() throws Exception;

    ProvinceModel create(ProvinceModel model) throws Exception;

}
