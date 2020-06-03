package com.mg.covid19.service;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.object.ProvinceObj;

import java.util.List;

public interface IProvinceService {

    List<ProvinceModel> getAll() throws Exception;

    List<ProvinceObj> getAllTree() throws Exception;

    ProvinceModel get(long id) throws Exception;

    ProvinceModel create(ProvinceModel provinceModel) throws Exception;

    ProvinceObj createTree(ProvinceObj provinceObj) throws Exception;

    ProvinceModel update(ProvinceModel provinceModel) throws Exception;

    String delete (Long id) throws Exception;


}
