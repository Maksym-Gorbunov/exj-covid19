package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
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
        List<Location> locations = repository.findAll();
        if (locations == null) {
            throw new ResourceNotFoundException("resource 'location' not found");
        }
        if (locations.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(locations);
    }

    @Override
    public LocationModel get(long id) throws Exception {
        Location location = repository.getOne(id);
        return mapper.toModel(location);
    }

    @Override
    public LocationModel create(LocationModel locationModel) throws Exception {
        Location location = mapper.toEntity(locationModel);
        Location savedLocation = repository.save(location);
        if (savedLocation == null) {
            throw new ResourceCreationException("unable to save 'location'");
        }
        return mapper.toModel(savedLocation);
    }

    @Override
    public LocationModel update(LocationModel locationModel) throws Exception {
        Location entity = repository.getOne(locationModel.getId());
        if (entity == null) {
            throw new ResourceCreationException("unable to update 'location'");
        }
        entity = mapper.toEntity(locationModel);
        Location savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Location with id '" + id + "' was successfully deleted";
    }

}

