package com.virtualpet.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import com.virtualpet.models.Views;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.services.ProfileService;
import com.virtualpet.services.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/profile")
@CrossOrigin(value = "*", maxAge = 3600L)
public class ProfileController {

    private ProfileServiceImpl profileService;

    @Autowired
    public ProfileController(ProfileServiceImpl profileService){
        this.profileService = profileService;
    }

    @GetMapping("/getSubsForUser")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.SubView.class)
    public List<SubDTO> getSubsForUser(Authentication authentication){
        return profileService.getSubsForUser(authentication);
    }

    @PostMapping("/createSub")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createSub(@RequestBody SubRequest subRequest, Authentication authentication){
        return profileService.createSubForUser(subRequest, authentication);
    }

    @DeleteMapping("/deleteSubForUser/{subId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteSub(@PathVariable("subId") long id, Authentication authentication){
        return profileService.deleteSubForUser(id, authentication);
    }
    @GetMapping("/getSubTypes")
    public List<SubTypeDTO> getSubTypes(){
        return profileService.getSubTypes();
    }

    @GetMapping("/getInfoAboutSub")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.SubView.class)
    public SubDTO chooseSub(@RequestParam(value = "subId") long subId, @RequestParam(value = "userId") long userId, Authentication authentication){
        return profileService.chooseSub(subId, userId, authentication);
    }

}
