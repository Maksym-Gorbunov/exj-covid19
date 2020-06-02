package com.mg.covid19.service.implementation;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Country;
import com.mg.covid19.model.entity.Location;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.model.LocationModel;
import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.model.object.CountryTree;
import com.mg.covid19.repository.CountryRepository;
import com.mg.covid19.service.ICountryService;
import com.mg.covid19.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private CountryRepository repository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private LocationService locationService;


    @Override
    public List<CountryModel> getAll() throws Exception {
        System.out.println(1111);
        List<Country> entities = repository.findAll();
        if (entities == null) {
            throw new ResourceNotFoundException("resource 'country' not found");
        }
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        System.out.println(222);
        return mapper.toModels(entities);
    }

    @Override
    public List<CountryTree> getAllTree() throws Exception {
        List<Country> countries = repository.findAll();
        if (countries == null) {
            throw new ResourceNotFoundException("resource 'country' not found");
        }
        if (countries.isEmpty()) {
            return new ArrayList<>();
        }
        List<CountryTree> result = new ArrayList<>();
        for (Country country : countries) {
            CountryTree countryTree = new CountryTree();
            countryTree.setName(country.getName());

            Statistic statistic = country.getStatistic();
            if (statistic != null) {
                countryTree.setStatistic(statisticService.get(statistic.getId()));
            }

            Code code = country.getCode();
            if (code != null) {
                countryTree.setCode(codeService.get(code.getId()));
            }

            Location location = country.getLocation();
            if(location != null){
                countryTree.setLocation(locationService.get(location.getId()));
            }

            result.add(countryTree);
        }
        return result;
    }

    @Override
    public CountryModel get(long id) throws Exception {
        Country entity = repository.getOne(id);
        return mapper.toModel(entity);
    }

    @Override
    public CountryModel create(CountryModel model) throws Exception {
        Country entity = mapper.toEntity(model);
        Country savedEntity = repository.save(entity);
        if (savedEntity == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }
        return mapper.toModel(savedEntity);
    }

    @Override
    public CountryTree createTree(CountryTree countryTree) throws Exception {
        CountryTree result = new CountryTree();
        Country country = new Country();
        country.setName(countryTree.getName());

        if(country.getStatistic()!=null){
            StatisticModel savedStatistic = statisticService.create(countryTree.getStatistic());
            if (savedStatistic == null) {
                throw new ResourceCreationException("unable to save 'statistic'");
            }
            country.setStatistic(mapper.toEntity(savedStatistic));
            result.setStatistic(savedStatistic);
        }



        CodeModel savedCode = codeService.create(countryTree.getCode());
        if (savedCode == null) {
            throw new ResourceCreationException("unable to save 'code'");
        }
        country.setCode(mapper.toEntity(savedCode));
        result.setCode(savedCode);

        LocationModel savedLocation = locationService.create(countryTree.getLocation());
        if (savedLocation == null) {
            throw new ResourceCreationException("unable to save 'location'");
        }
        country.setLocation(mapper.toEntity(savedLocation));
        result.setLocation(savedLocation);

        Country savedCountry = repository.save(country);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }


        return result;
    }

    @Override   //toDo Implement
    public CountryModel update(CountryModel model) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Country with id '" + id + "' was successfully deleted";
    }

}




