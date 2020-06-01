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
    private StatisticRepository statisticRepository;
    @Autowired
    private Mapper mapper;


    @Override
    public StatisticModel get(long id) throws Exception {
        Statistic entity = statisticRepository.getOne(id);
        return mapper.toModel(entity);
    }

    @Override
    public Iterable<StatisticModel> getAll() throws Exception {
        List<Statistic> entities = statisticRepository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'statistic' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(entities);
    }

    @Override
    public StatisticModel create(StatisticModel model) throws Exception {
        Statistic entity = mapper.toEntity(model);
        Statistic savedEntity = statisticRepository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'statistic'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override   //toDo Implement
    public StatisticModel update(StatisticModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        if (statisticRepository.getOne(id) == null) {
            throw new ResourceNotFoundException("unable to delete, statistic with id '" + id + "' not found");
        }
        statisticRepository.deleteById(id);
        return "Statistic with id '" + id + "' was successfully deleted";
    }

}

