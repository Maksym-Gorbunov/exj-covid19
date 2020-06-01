package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.repository.StatisticRepository;
import com.mg.covid19.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService implements IStatisticService {

    @Autowired
    private StatisticRepository repository;
    @Autowired
    private Mapper mapper = new Mapper();

    /*public StatisticService(StatisticRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }*/

    @Override
    public StatisticModel get(long id) throws Exception {
        System.out.println(111);
        Statistic statistic = new Statistic();
        System.out.println(222);
        statistic = repository.getOne(id);
        System.out.println("id "+statistic.getId());
        System.out.println("dea "+statistic.getDeaths());
        System.out.println(333);
        StatisticModel res = new StatisticModel();
        res = mapper.entityToModel(statistic);
        System.out.println("model dea "+res.getDeaths());   //toDo mapper return null???
        return res;
    }

    @Override
    public Iterable<StatisticModel> getAll() throws Exception {
        List<Statistic> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'statistic' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.entitiesToModels(entities);
    }

    @Override
    public StatisticModel create(StatisticModel model) throws Exception {
        Statistic entity = mapper.modelToEntity(model);
        Statistic savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'statistic'");
        }
        return mapper.entityToModel(savedEntity);
    }

    @Override   //toDo Implement
    public StatisticModel update(StatisticModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        if (repository.getOne(id) == null) {
            throw new ResourceNotFoundException("unable to delete, statistic with id '" + id + "' not found");
        }
        repository.deleteById(id);
        return "Statistic with id '" + id + "' was successfully deleted";
    }

}

