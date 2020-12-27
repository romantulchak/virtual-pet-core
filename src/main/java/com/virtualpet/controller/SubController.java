package com.virtualpet.controller;

import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.service.impl.SubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createSubType(@RequestBody SubTypeDTO subTypeDTO){
        return subService.createSub(subTypeDTO);
    }

    @DeleteMapping("/deleteSubType/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSubType(@PathVariable("id") Long id){
        return subService.deleteSub(id);
    }


}
