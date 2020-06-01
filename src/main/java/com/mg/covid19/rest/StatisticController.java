package com.mg.covid19.rest;

import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.service.implementation.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/covid19/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    //public StatisticController(StatisticService service) {
      //  this.service = service;
    //}


    @GetMapping("/{id}")
    public ResponseEntity<StatisticModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<StatisticModel>> getAll() throws Exception {
        Iterable<StatisticModel> result = statisticService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<StatisticModel> create (@Valid @RequestBody StatisticModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(statisticService.create(model));
    }

    @PutMapping
    public ResponseEntity<StatisticModel> update (@Valid @RequestBody StatisticModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.update(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.delete(id));
    }

}
