package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.model.Views;
import com.virtualpet.service.impl.SubServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sub")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class SubController {

    private final SubServiceImpl subService;

    @PostMapping("/create-sub-type")
    @PreAuthorize("hasRole('ADMIN')")
    public void createSubType(@RequestPart(name = "subType") String subType, @RequestPart(name = "image") MultipartFile image) throws JsonProcessingException {
        subService.createSub(subType, image);
    }

    @GetMapping("/subtypes")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.SubTypeView.class)
    public List<SubTypeDTO> getSubtypes(){
        return subService.getSubtypes();
    }

    @DeleteMapping("/deleteSubType/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSubType(@PathVariable("id") Long id){
        subService.deleteSub(id);
    }


}
