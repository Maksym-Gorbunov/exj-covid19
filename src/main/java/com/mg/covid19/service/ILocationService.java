package com.mg.covid19.service;

import com.mg.covid19.model.entity.Location;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.LocationModel;

public interface ILocationService {

    Iterable<LocationModel> getAll() throws Exception;

    LocationModel get(long id) throws Exception;

    LocationModel create(LocationModel model) throws Exception;

    LocationModel update(LocationModel model) throws Exception;

    String delete (Long id) throws Exception;

}
