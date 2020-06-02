package com.mg.covid19.rest;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.object.ProvinceRequestObj;
import com.mg.covid19.model.object.ProvinceResponseObj;
import com.mg.covid19.service.implementation.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/covid19/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;


    @GetMapping("/{id}")
    public ResponseEntity<ProvinceModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProvinceModel>> getAll() throws Exception {
        Iterable<ProvinceModel> result = provinceService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/*")
    public ResponseEntity<List<ProvinceResponseObj>> getAllTree() throws Exception {
        List<ProvinceResponseObj> result = provinceService.getAllTree();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<ProvinceModel> create (@Valid @RequestBody ProvinceModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.create(model));
    }

    @PostMapping("/*")
    public ResponseEntity<ProvinceResponseObj> createTree (@Valid @RequestBody ProvinceRequestObj provinceRequestObj) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.createTree(provinceRequestObj));
    }

    @PutMapping
    public ResponseEntity<ProvinceModel> update (@Valid @RequestBody ProvinceModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.update(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.delete(id));
    }

}
