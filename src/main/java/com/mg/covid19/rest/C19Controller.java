package com.mg.covid19.rest;

import com.mg.covid19.model.StatisticModel;
import com.mg.covid19.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/covid19/statistic")
public class C19Controller {

    private StatisticsService statisticsService;
    public C19Controller(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<StatisticModel>> getAllStatistic() throws Exception {
        Iterable<StatisticModel> result = statisticsService.getAllStatistic();
        result.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<StatisticModel> createStatistic (@Valid @RequestBody StatisticModel statisticModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(statisticsService.createStatistic(statisticModel));
    }



    /*
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById (@PathVariable Long userId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }


    @PostMapping
    public ResponseEntity<UserModel> createUser (@Valid @RequestBody UserModel userModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userModel));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) throws Exception {
        String msg = userService.deleteUserById(userId);
        System.out.println("msg: "+msg);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    */


}
