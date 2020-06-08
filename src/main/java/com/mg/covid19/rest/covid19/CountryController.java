package com.mg.covid19.rest.covid19;

import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.object.CountryObj;
import com.mg.covid19.service.implementation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
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
    public ResponseEntity<List<CountryObj>> getAllTree() throws Exception {
        List<CountryObj> result = countryService.getAllTree();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<CountryModel> create (@Valid @RequestBody CountryModel countryModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(countryModel));
    }

    @PostMapping("/*")
    public ResponseEntity<CountryObj> createTree (@Valid @RequestBody CountryObj countryRequestObj) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.createTree(countryRequestObj));
    }

    @PatchMapping
    public ResponseEntity<CountryModel> update (@Valid @RequestBody CountryModel countryModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(countryService.update(countryModel));
    }

    @PatchMapping("/*")
    public ResponseEntity<CountryObj> updateTree (@Valid @RequestBody CountryObj countryObj ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(countryService.updateTree(countryObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.delete(id));
    }

}
