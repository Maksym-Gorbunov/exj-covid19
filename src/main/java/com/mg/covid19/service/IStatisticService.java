package com.mg.covid19.service;

import com.mg.covid19.model.model.StatisticModel;

public interface IStatisticService {

    StatisticModel get(long id) throws Exception;

    Iterable<StatisticModel> getAll() throws Exception;

    StatisticModel create(StatisticModel model) throws Exception;

    StatisticModel update(StatisticModel model) throws Exception;

    String delete (Long id) throws Exception;

}

