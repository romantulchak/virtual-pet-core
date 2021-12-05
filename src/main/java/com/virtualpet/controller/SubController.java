package com.virtualpet.controller;

import com.virtualpet.model.SubType;
import com.virtualpet.service.impl.SubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/sub")
@CrossOrigin(value = "*", maxAge = 3600L)
public class SubController {

    private SubServiceImpl subService;

    @Autowired
    public SubController(SubServiceImpl subService){
        this.subService = subService;
    }

    @PostMapping("/createSubType")
    @PreAuthorize("hasRole('ADMIN')")
    public void createSubType(@RequestBody SubType subType){
        subService.createSub(subType);
    }
    @PostMapping("/uploadSubTypeImage")
    @PreAuthorize("hasRole('ADMIN')")
    public void uploadSubTypeImage(@RequestBody MultipartFile file){
        subService.uploadFile(file);
    }

    @DeleteMapping("/deleteSubType/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSubType(@PathVariable("id") Long id){
        subService.deleteSub(id);
    }


}
