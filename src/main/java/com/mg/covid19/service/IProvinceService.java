package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.response.ProvinceResponce;

import java.util.List;

public interface IProvinceService {

    List<ProvinceModel> getAll() throws Exception;

    List<ProvinceResponce> getAllTree() throws Exception;

    ProvinceModel get(long id) throws Exception;

    ProvinceModel create(ProvinceModel model) throws Exception;

    ProvinceModel update(ProvinceModel model) throws Exception;

    String delete (Long id) throws Exception;


}
