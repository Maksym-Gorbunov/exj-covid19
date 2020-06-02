package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Province;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.model.object.ProvinceTree;
import com.mg.covid19.repository.ProvinceRepository;
import com.mg.covid19.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private ProvinceRepository repository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private StatisticService statisticService;


    @Override
    public List<ProvinceModel> getAll() throws Exception {
        List<Province> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(entities);
    }

    @Override
    public List<ProvinceTree> getAllTree() throws Exception {
        List<Province> provinces = repository.findAll();
        if (provinces == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if (provinces.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProvinceTree> result = new ArrayList<>();
        for (Province province : provinces) {
            ProvinceTree provinceTree = new ProvinceTree();
            provinceTree.setName(province.getName());
            Statistic statistic = province.getStatistic();
            if (statistic != null) {
                provinceTree.setStatistic(statisticService.get(statistic.getId()));
            }
            result.add(provinceTree);
        }
        return result;
    }

    @Override
    public ProvinceModel get(long id) throws Exception {
        Province entity = repository.getOne(id);
        return mapper.toModel(entity);
    }

    @Override
    public ProvinceModel create(ProvinceModel model) throws Exception {
        Province entity = mapper.toEntity(model);
        Province savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'province'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override
    public ProvinceTree createTree(ProvinceTree provinceTree) throws Exception {
        Province province = new Province();
        province.setName(provinceTree.getName());
        StatisticModel savedStatistic = statisticService.create(provinceTree.getStatistic());
        province.setStatistic(mapper.toEntity(savedStatistic));
        Province savedProvince = repository.save(province);
        if (savedProvince == null) {
            throw new ResourceCreationException("unable to save 'province'");
        }
        if (savedStatistic == null) {
            throw new ResourceCreationException("unable to save 'statistic'");
        }
        ProvinceTree result = new ProvinceTree();
        result.setName(savedProvince.getName());
        result.setStatistic(mapper.toModel(savedStatistic));
        return result;
    }

    @Override   //toDo Implement
    public ProvinceModel update(ProvinceModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Province with id '" + id + "' was successfully deleted";
    }

}




