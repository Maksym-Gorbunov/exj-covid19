package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Location;
import com.mg.covid19.model.model.LocationModel;
import com.mg.covid19.repository.LocationRepository;
import com.mg.covid19.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private LocationRepository repository;
    @Autowired
    private Mapper mapper;


    @Override
    public Iterable<LocationModel> getAll() throws Exception {
        List<Location> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'location' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(entities);
    }

    @Override
    public LocationModel get(long id) throws Exception {
        Location entity = repository.getOne(id);
        return mapper.toModel(entity);
    }

    @Override
    public LocationModel create(LocationModel model) throws Exception {
        Location entity = mapper.toEntity(model);
        Location savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'location'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override   //toDo Implement
    public LocationModel update(LocationModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Location with id '" + id + "' was successfully deleted";
    }

}

