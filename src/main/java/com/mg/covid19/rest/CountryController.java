package com.mg.covid19.rest;

import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.object.CountryTree;
import com.mg.covid19.service.implementation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/covid19/country")
public class CountryController {
    @Autowired
    private CountryService countryService;


    @GetMapping("/{id}")
    public ResponseEntity<CountryModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<CountryModel>> getAll() throws Exception {
        Iterable<CountryModel> result = countryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/*")
    public ResponseEntity<List<CountryTree>> getAllTree() throws Exception {
        List<CountryTree> result = countryService.getAllTree();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<CountryModel> create (@Valid @RequestBody CountryModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(model));
    }

    @PostMapping("/*")
    public ResponseEntity<CountryTree> createTree (@Valid @RequestBody CountryTree countryTree) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.createTree(countryTree));
    }

    @PutMapping
    public ResponseEntity<CountryModel> update (@Valid @RequestBody CountryModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(countryService.update(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.delete(id));
    }

}
