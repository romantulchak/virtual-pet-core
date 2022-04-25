package com.virtualpet.controller;

import com.virtualpet.model.SubType;
import com.virtualpet.service.impl.SubServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/sub")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class SubController {

    private final SubServiceImpl subService;

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
