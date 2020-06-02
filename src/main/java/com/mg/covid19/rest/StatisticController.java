package com.mg.covid19.rest;

import com.mg.covid19.model.model.StatisticModel;
import com.mg.covid19.service.implementation.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService service;


    @GetMapping("/{id}")
    public ResponseEntity<StatisticModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<StatisticModel>> getAll() throws Exception {
        Iterable<StatisticModel> result = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<StatisticModel> create (@Valid @RequestBody StatisticModel statisticModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(statisticModel));
    }

    @PutMapping
    public ResponseEntity<StatisticModel> update (@Valid @RequestBody StatisticModel statisticModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(statisticModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}
