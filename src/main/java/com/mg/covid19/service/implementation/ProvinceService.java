package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Province;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.response.ProvinceResponce;
import com.mg.covid19.repository.CodeRepository;
import com.mg.covid19.repository.ProvinceRepository;
import com.mg.covid19.repository.StatisticRepository;
import com.mg.covid19.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    private ProvinceRepository repository;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private Mapper mapper;




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
    public Iterable<ProvinceResponce> getAllTree() throws Exception {
        List<Province> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if(entities.isEmpty()){
            return new ArrayList<>();
        }
        List<ProvinceResponce> provinceResponces = new ArrayList<>();
        entities.forEach(e -> {
            ProvinceResponce provinceResponce = new ProvinceResponce();
            provinceResponce.setProvinceModel(mapper.entityToModel(e));
            provinceResponces.add(provinceResponce);
        });

        return provinceResponces;
    }

    @Override
    public ProvinceModel create(ProvinceModel model) throws Exception {
        Province entity = mapper.modelToEntity(model);
        //Statistic statistic = statisticRepository.getOne(1L);
        //System.out.println(statistic);
        //entity.setStatistic(statistic);
        //user.setCreated(Instant.now());
        Province savedEntity = repository.save(entity);
        if(savedEntity == null){
            throw new ResourceCreationException("unable to save 'province'");
        }
        return mapper.entityToModel(savedEntity);
    }


}
