package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.repository.CodeRepository;
import com.mg.covid19.service.ICodeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService implements ICodeService {

    private CodeRepository repository;
    private Mapper mapper = new Mapper();

    public CodeService(CodeRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Iterable<CodeModel> getAll() throws Exception {
        List<Code> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'code' not found");
        }
        if(entities.isEmpty()){
            return new ArrayList<>();
        }
        return mapper.entitiesToModels(entities);
    }

    @Override
    public CodeModel create(CodeModel model) throws Exception {
        Code entity = mapper.modelToEntity(model);
        //user.setCreated(Instant.now());
        Code savedEntity = repository.save(entity);
        if(savedEntity == null){
            throw new ResourceCreationException("unable to save 'code'");
        }
        return mapper.entityToModel(savedEntity);
    }

}