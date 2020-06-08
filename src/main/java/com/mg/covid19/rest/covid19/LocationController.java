package com.mg.covid19.rest.covid19;

import com.mg.covid19.model.model.LocationModel;
import com.mg.covid19.service.implementation.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService service;


    @GetMapping("/{id}")
    public ResponseEntity<LocationModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<LocationModel>> getAll() throws Exception {
        Iterable<LocationModel> result = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<LocationModel> create (@Valid @RequestBody LocationModel locationModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(locationModel));
    }

    @PatchMapping
    public ResponseEntity<LocationModel> update (@Valid @RequestBody LocationModel locationModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(locationModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}
