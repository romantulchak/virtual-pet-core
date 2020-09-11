package com.virtualpet.controllers;

import com.virtualpet.dtos.SubDTO;
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
    public List<SubDTO> getSubsForUser(Authentication authentication){
        return profileService.getSubsForUser(authentication);
    }

    @PostMapping("/createSub")
    @PreAuthorize("hasRole('USER') and @userSecurity.hasUserId(authentication, #subRequest.user.id)")
    public ResponseEntity<?> createSub(@RequestBody SubRequest subRequest){
        return profileService.createSubForUser(subRequest);
    }


}
