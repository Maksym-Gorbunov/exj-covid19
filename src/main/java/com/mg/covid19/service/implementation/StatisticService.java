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
    private Mapper mapper;


    @Override
    public Iterable<StatisticModel> getAll() throws Exception {
        List<Statistic> statistics = repository.findAll();
        if (statistics == null) {
            throw new ResourceNotFoundException("resource 'statistic' not found");
        }
        if (statistics.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(statistics);
    }

    @Override
    public StatisticModel get(long id) throws Exception {
        Statistic statistic = repository.getOne(id);
        return mapper.toModel(statistic);
    }

    @Override
    public StatisticModel create(StatisticModel statisticModel) throws Exception {
        Statistic statistic = mapper.toEntity(statisticModel);
        Statistic savedStatistic = repository.save(statistic);
        if (savedStatistic == null) {
            throw new ResourceCreationException("unable to save 'statistic'");
        }
        return mapper.toModel(savedStatistic);
    }

    @Override   //toDo Implement
    public StatisticModel update(StatisticModel statisticModel) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Statistic with id '" + id + "' was successfully deleted";
    }

}

