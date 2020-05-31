package com.mg.covid19.service;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.model.StatisticModel;

public interface IStatisticService {

    Iterable<StatisticModel> getAllStatistic() throws Exception;

    StatisticModel createStatistic(StatisticModel statisticModel) throws Exception;
}


/*
* Iterable<UserModel> getUsers() throws Exception;

    UserModel getUserById(Long userId) throws Exception;

    UserModel createUser(UserModel userModel) throws Exception;

    String deleteUserById(Long userId) throws Exception;

    //UserModel updateUser(UserModel user, long userId) throws Exception;

    //String getPasswordByEmail(String email) throws Exception;
* */