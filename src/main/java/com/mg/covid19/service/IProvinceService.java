package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.object.ProvinceRequestObj;
import com.mg.covid19.model.object.ProvinceResponseObj;

import java.util.List;

public interface IProvinceService {

    List<ProvinceModel> getAll() throws Exception;

    List<ProvinceResponseObj> getAllTree() throws Exception;

    ProvinceModel get(long id) throws Exception;

    ProvinceModel create(ProvinceModel model) throws Exception;

    ProvinceResponseObj createTree(ProvinceRequestObj provinceRequestObj) throws Exception;

    ProvinceModel update(ProvinceModel model) throws Exception;

    String delete (Long id) throws Exception;


}
