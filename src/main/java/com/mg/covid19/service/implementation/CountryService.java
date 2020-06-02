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
import com.mg.covid19.model.object.CountryRequestObj;
import com.mg.covid19.model.object.CountryResponseObj;
import com.mg.covid19.repository.CountryRepository;
import com.mg.covid19.service.ICountryService;
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
        List<Country> countries = repository.findAll();
        if (countries == null) {
            throw new ResourceNotFoundException("resource 'country' not found");
        }
        if (countries.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toModels(countries);
    }

    @Override
    public List<CountryResponseObj> getAllTree() throws Exception {
        List<Country> countries = repository.findAll();
        if (countries == null) {
            throw new ResourceNotFoundException("resource 'country' not found");
        }
        if (countries.isEmpty()) {
            return new ArrayList<>();
        }
        List<CountryResponseObj> result = new ArrayList<>();
        for (Country country : countries) {
            CountryResponseObj countryResponseObj = new CountryResponseObj();
            countryResponseObj.setName(country.getName());

            Statistic statistic = country.getStatistic();
            if (statistic != null) {
                countryResponseObj.setStatistic(statisticService.get(statistic.getId()));
            }

            Code code = country.getCode();
            if (code != null) {
                countryResponseObj.setCode(codeService.get(code.getId()));
            }

            Location location = country.getLocation();
            if (location != null) {
                countryResponseObj.setLocation(locationService.get(location.getId()));
            }

            result.add(countryResponseObj);
        }
        return result;
    }

    @Override
    public CountryModel get(long id) throws Exception {
        Country country = repository.getOne(id);
        return mapper.toModel(country);
    }

    @Override
    public CountryModel create(CountryModel countryModel) throws Exception {
        Country country = mapper.toEntity(countryModel);
        Country savedCountry = repository.save(country);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }
        return mapper.toModel(savedCountry);
    }

    @Override
    public CountryResponseObj createTree(CountryRequestObj countryRequestObj) throws Exception {
        CountryResponseObj result = new CountryResponseObj();

        Country country = new Country();
        country.setName(countryRequestObj.getName());

        StatisticModel statisticModel = countryRequestObj.getStatistic();
        if (statisticModel != null) {
            StatisticModel savedStatisticModel = statisticService.create(statisticModel);
            if (savedStatisticModel == null) {
                throw new ResourceCreationException("unable to save 'statistic'");
            }
            country.setStatistic(mapper.toEntity(savedStatisticModel));
            result.setStatistic(savedStatisticModel);
        }

        CodeModel codeModel = countryRequestObj.getCode();
        if (codeModel != null) {
            CodeModel savedCodeModel = codeService.create(codeModel);
            if (savedCodeModel == null) {
                throw new ResourceCreationException("unable to save 'code'");
            }
            country.setCode(mapper.toEntity(savedCodeModel));
            result.setCode(savedCodeModel);
        }

        LocationModel locationModel = countryRequestObj.getLocation();
        if (locationModel != null) {
            LocationModel savedLocationModel = locationService.create(locationModel);
            if (savedLocationModel == null) {
                throw new ResourceCreationException("unable to save 'location'");
            }
            country.setLocation(mapper.toEntity(savedLocationModel));
            result.setLocation(savedLocationModel);
        }

        Country savedCountry = repository.save(country);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }

        result.setName(savedCountry.getName());

        return result;
    }

    @Override   //toDo Implement
    public CountryModel update(CountryModel countryModel) throws Exception {
        return null;
    }

    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Country with id '" + id + "' was successfully deleted";
    }

}




