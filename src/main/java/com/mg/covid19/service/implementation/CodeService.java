package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.repository.CodeRepository;
import com.mg.covid19.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService implements ICodeService {
    @Autowired
    private CodeRepository repository;
    @Autowired
    private Mapper mapper;


    @Override
    public Iterable<CodeModel> getAll() throws Exception {
        List<Code> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'code' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(entities);
    }

    @Override
    public CodeModel get(long id) throws Exception {
        Code entity = repository.getOne(id);
        return mapper.toModel(entity);
    }

    @Override
    public CodeModel create(CodeModel model) throws Exception {
        Code entity = mapper.toEntity(model);
        Code savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'code'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override   //toDo Implement
    public CodeModel update(CodeModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Code with id '" + id + "' was successfully deleted";
    }

}

