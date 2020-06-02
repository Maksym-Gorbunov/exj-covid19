package com.mg.covid19.service;

import com.mg.covid19.model.model.StatisticModel;

public interface IStatisticService {

    Iterable<StatisticModel> getAll() throws Exception;

    StatisticModel get(long id) throws Exception;

    StatisticModel create(StatisticModel statisticModel) throws Exception;

    StatisticModel update(StatisticModel statisticModel) throws Exception;

    String delete (Long id) throws Exception;

}

