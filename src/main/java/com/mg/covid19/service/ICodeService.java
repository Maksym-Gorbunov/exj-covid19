package com.mg.covid19.service;

import com.mg.covid19.model.model.CodeModel;

public interface ICodeService {

    Iterable<CodeModel> getAll() throws Exception;

    CodeModel get(long id) throws Exception;

    CodeModel create(CodeModel model) throws Exception;

    CodeModel update(CodeModel model) throws Exception;

    String delete (Long id) throws Exception;
    
}