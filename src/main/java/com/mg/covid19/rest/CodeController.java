package com.mg.covid19.rest;

import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.service.implementation.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private CodeService service;


    @GetMapping("/{id}")
    public ResponseEntity<CodeModel> get (@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<CodeModel>> getAll() throws Exception {
        Iterable<CodeModel> result = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<CodeModel> create (@Valid @RequestBody CodeModel codeModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(codeModel));
    }

    @PutMapping
    public ResponseEntity<CodeModel> update (@Valid @RequestBody CodeModel codeModel ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(codeModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}
