package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Province;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.repository.CodeRepository;
import com.mg.covid19.repository.ProvinceRepository;
import com.mg.covid19.service.IProvinceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService implements IProvinceService {

    private ProvinceRepository repository;
    private Mapper mapper = new Mapper();

    public ProvinceService(ProvinceRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Iterable<ProvinceModel> getAll() throws Exception {
        List<Province> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if(entities.isEmpty()){
            return new ArrayList<>();
        }
        return mapper.entitiesToModels(entities);
    }

    @Override
    public ProvinceModel create(ProvinceModel model) throws Exception {
        Province entity = mapper.modelToEntity(model);
        //user.setCreated(Instant.now());
        Province savedEntity = repository.save(entity);
        if(savedEntity == null){
            throw new ResourceCreationException("unable to save 'province'");
        }
        return mapper.entityToModel(savedEntity);
    }
}
