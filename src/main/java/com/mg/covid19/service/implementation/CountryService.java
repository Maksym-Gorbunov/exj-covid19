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
import com.mg.covid19.model.object.CountryObj;
import com.mg.covid19.repository.CodeRepository;
import com.mg.covid19.repository.CountryRepository;
import com.mg.covid19.repository.LocationRepository;
import com.mg.covid19.repository.StatisticRepository;
import com.mg.covid19.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    public List<CountryObj> getAllTree() throws Exception {
        List<Country> countries = repository.findAll();
        if (countries == null) {
            throw new ResourceNotFoundException("resource 'country' not found");
        }
        if (countries.isEmpty()) {
            return new ArrayList<>();
        }
        List<CountryObj> result = new ArrayList<>();
        for (Country country : countries) {
            CountryObj countryObj = new CountryObj();
            countryObj.setName(country.getName());
            countryObj.setId(country.getId());

            Statistic statistic = country.getStatistic();
            if (statistic != null) {
                countryObj.setStatistic(statisticService.get(statistic.getId()));
            }

            Code code = country.getCode();
            if (code != null) {
                countryObj.setCode(codeService.get(code.getId()));
            }

            Location location = country.getLocation();
            if (location != null) {
                countryObj.setLocation(locationService.get(location.getId()));
            }

            result.add(countryObj);
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
        System.out.println("555 " + country);
        Country savedCountry = repository.save(country);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }
        return mapper.toModel(savedCountry);
    }


    /*
    @Override
    public CountryObj createTree(CountryObj countryObj) throws Exception {
        CountryObj result = new CountryObj();

        Country country = new Country();
        country.setName(countryObj.getName());
        if (countryObj.getCode() != null) {
            country.setCode(mapper.toEntity(countryObj.getCode()));
            result.setCode(codeService.create(countryObj.getCode()));
        }
        if (countryObj.getLocation() != null) {
            country.setLocation(mapper.toEntity(countryObj.getLocation()));
            result.setLocation(locationService.create(countryObj.getLocation()));
        }
        if (countryObj.getStatistic() != null) {
            country.setStatistic(mapper.toEntity(countryObj.getStatistic()));
            result.setStatistic(statisticService.create(countryObj.getStatistic()));
        }
        System.out.println(444);
        Country savedCountry = repository.save(country);
        System.out.println(555);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }

        result.setId(savedCountry.getId());
        result.setName(savedCountry.getName());

        return result;
    }
    */

    @Override
    public CountryObj createTree(CountryObj countryObj) throws Exception {
        CountryObj result = new CountryObj();

        Country country = new Country();
        country.setName(countryObj.getName());
        country.setCode(mapper.toEntity(countryObj.getCode()));
        country.setLocation(mapper.toEntity(countryObj.getLocation()));
        country.setStatistic(mapper.toEntity(countryObj.getStatistic()));
        Country savedCountry = repository.save(country);
        if (savedCountry == null) {
            throw new ResourceCreationException("unable to save 'country'");
        }

        result.setId(savedCountry.getId());
        result.setName(savedCountry.getName());
        if(countryObj.getCode()!=null){
            result.setCode(codeService.get(savedCountry.getCode().getId()));
        }
        if(countryObj.getLocation()!=null){
            result.setLocation(locationService.get(savedCountry.getLocation().getId()));
        }
        if(countryObj.getStatistic()!=null){
            result.setStatistic(statisticService.get(savedCountry.getStatistic().getId()));
        }

        return result;
    }

    @Override
    public List<CountryObj> createTrees(List<CountryObj> countriesObj) throws Exception {
        List<CountryObj> result = new ArrayList<>();
        for (int i = 0; i < countriesObj.size(); i++) {
            result.add(createTree(countriesObj.get(i)));
            System.out.println((i + 1) + "/" + countriesObj.size() + " inserted to database");
        }
        return result;
    }

    @Override
    public CountryModel update(CountryModel countryModel) throws Exception {
        Country entity = repository.getOne(countryModel.getId());
        if (entity == null) {
            throw new ResourceCreationException("unable to update 'country'");
        }
        entity.setName(countryModel.getName());
        Country savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Override
    public CountryObj updateTree(CountryObj countryObj) throws Exception {
        Country country = repository.getOne(countryObj.getId());
        if (country == null) {
            throw new ResourceCreationException("unable to update 'country'");
        }

        if(countryObj.getCode()!=null){
            if(country.getCode()!=null){
                codeService.update(countryObj.getCode());
            } else {
                country.setCode(mapper.toEntity(countryObj.getCode()));
            }
        }
        if(countryObj.getLocation()!=null){
            if(country.getLocation()!=null){
                locationService.update(countryObj.getLocation());
            } else {
                country.setLocation(mapper.toEntity(countryObj.getLocation()));
            }
        }
        if(countryObj.getStatistic()!=null){
            if(country.getStatistic()!=null){
                statisticService.update(countryObj.getStatistic());
            } else {
                country.setStatistic(mapper.toEntity(countryObj.getStatistic()));
            }
        }

        country.setName(countryObj.getName());
        Country savedCountry = repository.save(country);

        CountryObj result = new CountryObj();
        result.setId(savedCountry.getId());
        result.setName(savedCountry.getName());
        if(countryObj.getCode()!=null){
            result.setCode(codeService.get(savedCountry.getCode().getId()));
        }
        if(countryObj.getLocation()!=null){
            result.setLocation(locationService.get(savedCountry.getLocation().getId()));
        }
        if(countryObj.getStatistic()!=null){
            result.setStatistic(statisticService.get(savedCountry.getStatistic().getId()));
        }

        return result;
    }


    @Override
    public String delete(Long id) throws Exception {
        repository.getOne(id);
        repository.deleteById(id);
        return "Country with id '" + id + "' was successfully deleted";
    }

}




