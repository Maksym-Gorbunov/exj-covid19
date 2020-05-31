package com.mg.covid19.service;


import com.mg.covid19.model.model.CodeModel;

public interface ICodeService {

    Iterable<CodeModel> getAll() throws Exception;

    CodeModel create(CodeModel model) throws Exception;

}


/*
* Iterable<UserModel> getUsers() throws Exception;

    UserModel getUserById(Long userId) throws Exception;

    UserModel createUser(UserModel userModel) throws Exception;

    String deleteUserById(Long userId) throws Exception;

    //UserModel updateUser(UserModel user, long userId) throws Exception;

    //String getPasswordByEmail(String email) throws Exception;
* */
