package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.model.User;
import com.virtualpet.model.UserFriend;
import com.virtualpet.model.Views;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    @GetMapping("/getFriends")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.UserView.class)
    public Set<UserDTO> friends(Authentication authentication){
        return profileService.getFriends(authentication);
    }

    @PostMapping("/friendRequest")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> friendRequest(@RequestBody User user, Authentication authentication){
        boolean result = profileService.friendRequest(authentication, user);
        return result ? new ResponseEntity<>("Request sent", HttpStatus.OK) : new ResponseEntity<>("Something wrong", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getUserByUsername/{username}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.FriendView.class)
    public UserDTO getUserByUsername(@PathVariable("username") String username, Authentication authentication){
        return profileService.getUserByUsername(username, authentication);
    }

    @GetMapping("/getFriendRequests")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.FriendView.class)
    public Set<UserFriend> getFriendRequests(Authentication authentication){
        return profileService.getFriendsRequest(authentication);

    }
    @GetMapping("/getFriendResponse")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.FriendView.class)
    public Set<UserFriend> getFriendResponse(Authentication authentication){
        return profileService.getFriendsResponse(authentication);
    }

}
