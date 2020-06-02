package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Province;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.model.object.ProvinceRequestObj;
import com.mg.covid19.model.object.ProvinceResponseObj;
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
        List<Province> provinces = repository.findAll();
        if (provinces == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if (provinces.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(provinces);
    }

    @Override
    public List<ProvinceResponseObj> getAllTree() throws Exception {
        List<Province> provinces = repository.findAll();
        if (provinces == null) {
            throw new ResourceNotFoundException("resource 'province' not found");
        }
        if (provinces.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProvinceResponseObj> result = new ArrayList<>();
        for (Province province : provinces) {
            ProvinceResponseObj provinceResponseObj = new ProvinceResponseObj();
            provinceResponseObj.setName(province.getName());
            Statistic statistic = province.getStatistic();
            if (statistic != null) {
                provinceResponseObj.setStatistic(statisticService.get(statistic.getId()));
            }
            result.add(provinceResponseObj);
        }
        return result;
    }

    @Override
    public ProvinceModel get(long id) throws Exception {
        Province province = repository.getOne(id);
        return mapper.toModel(province);
    }

    @Override
    public ProvinceModel create(ProvinceModel provinceModel) throws Exception {
        Province province = mapper.toEntity(provinceModel);
        Province savedProvince = repository.save(province);
        if (savedProvince == null) {
            throw new ResourceCreationException("unable to save 'province'");
        }
        return mapper.toModel(savedProvince);
    }

    @Override
    public ProvinceResponseObj createTree(ProvinceRequestObj provinceRequestObj) throws Exception {
        ProvinceResponseObj result = new ProvinceResponseObj();

        Province province = new Province();
        province.setName(provinceRequestObj.getName());

        StatisticModel statisticModel = provinceRequestObj.getStatistic();
        if(statisticModel!=null){
            StatisticModel savedStatisticModel = statisticService.create(statisticModel);
            if (savedStatisticModel == null) {
                throw new ResourceCreationException("unable to save 'statistic'");
            }
            province.setStatistic(mapper.toEntity(savedStatisticModel));
            result.setStatistic(mapper.toModel(savedStatisticModel));
        }

        Province savedProvince = repository.save(province);
        if (savedProvince == null) {
            throw new ResourceCreationException("unable to save 'province'");
        }
        result.setName(savedProvince.getName());

        return result;
    }

    @Override   //toDo Implement
    public ProvinceModel update(ProvinceModel provinceModel) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Province with id '" + id + "' was successfully deleted";
    }

}




