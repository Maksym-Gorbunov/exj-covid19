package com.mg.covid19.service;

import com.mg.covid19.config.exception.exc.ResourceCreationException;
import com.mg.covid19.config.exception.exc.ResourceNotFoundException;
import com.mg.covid19.model.Mapper;
import com.mg.covid19.model.Statistic;
import com.mg.covid19.model.StatisticModel;
import com.mg.covid19.repository.StatisticRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService implements IStatisticService {

    private StatisticRepository statisticRepository;
    private Mapper mapper = new Mapper();

    public StatisticsService(StatisticRepository statisticRepository, Mapper myModelMapper) {
        this.statisticRepository = statisticRepository;
        this.mapper = myModelMapper;
    }


    @Override
    public Iterable<StatisticModel> getAllStatistic() throws Exception {
        List<Statistic> statistic = statisticRepository.findAll();
        if (statistic == null) {
            throw new ResourceNotFoundException("resource 'statistic' not found");
        }
        if(statistic.isEmpty()){
            return new ArrayList<>();
        }
        return mapper.entitiesToModels(statistic);
    }

    @Override
    public StatisticModel createStatistic(StatisticModel statisticModel) throws Exception {
        Statistic statistic = mapper.modelToEntity(statisticModel);
        //user.setCreated(Instant.now());
        Statistic savedStatistic = statisticRepository.save(statistic);
        if(savedStatistic == null){
            throw new ResourceCreationException("unable to save 'statistic'");
        }
        return mapper.entityToModel(savedStatistic);
    }

}


/*


package com.mg.exj.service.user;

import com.mg.exj.config.exception.exc.ResourceCreationException;
import com.mg.exj.model.user.User;
import com.mg.exj.config.exception.exc.ResourceNotFoundException;
import com.mg.exj.model.Mapper;
import com.mg.exj.model.user.UserModel;
import com.mg.exj.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    private Mapper mapper = new Mapper();

    public UserService(UserRepository userRepository, Mapper myModelMapper) {
        this.userRepository = userRepository;
        this.mapper = myModelMapper;
    }

    @Override
    public Iterable<UserModel> getUsers() throws Exception {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        System.out.println(users.size());
        if (users == null) {
            throw new ResourceNotFoundException("resource not found exception while trying to read all users");
        }
        if(users.isEmpty()){
            return new ArrayList<UserModel>();
        }
        return mapper.entitiesToModels(users);
    }

    @Override
    public UserModel getUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(String.format("user with id '%s' not found", userId));
        }
        return mapper.entityToModel(user.get());
    }

    @Override
    public UserModel createUser(UserModel userModel) throws Exception {
        User user = mapper.modelToEntity(userModel);
        user.setCreated(Instant.now());
        user.setPassword("123");
        User savedUser = userRepository.save(user);
        if(savedUser == null){
            throw new ResourceCreationException("user not saved");
        }
        return mapper.entityToModel(savedUser);
    }

    @Override
    public String deleteUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(String.format("unable to delete, user with id '%s' not found", userId));
        }
        userRepository.deleteById(userId);
        return String.format("User with id '%s' was successfully removed", userId);
    }


}


* */