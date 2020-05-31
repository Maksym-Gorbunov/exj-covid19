package com.mg.covid19.rest;

import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.service.implementation.CodeService;
import com.mg.covid19.service.implementation.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/covid19/province")
public class ProvinceController {

    private ProvinceService service;
    public ProvinceController(ProvinceService service) {
        this.service = service;
    }


    @GetMapping()
    public ResponseEntity<Iterable<ProvinceModel>> getAll() throws Exception {
        Iterable<ProvinceModel> result = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<ProvinceModel> create (@Valid @RequestBody ProvinceModel model ) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(model));
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
