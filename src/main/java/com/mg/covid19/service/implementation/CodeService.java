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
        List<Code> codes = repository.findAll();
        if (codes == null) {
            throw new ResourceNotFoundException("resource 'code' not found");
        }
        if (codes.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(codes);
    }

    @Override
    public CodeModel get(long id) throws Exception {
        Code code = repository.getOne(id);
        return mapper.toModel(code);
    }

    @Override
    public CodeModel create(CodeModel codeModel) throws Exception {
        Code entity = mapper.toEntity(codeModel);
        Code savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'code'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override   //toDo Implement
    public CodeModel update(CodeModel codeModel) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Code with id '" + id + "' was successfully deleted";
    }

}

