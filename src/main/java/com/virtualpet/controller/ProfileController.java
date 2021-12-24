package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.MoneyCurrencyDTO;
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

import javax.swing.text.View;
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
    @PreAuthorize("isAuthenticated()")
    @JsonView(Views.SubView.class)
    public List<SubDTO> getSubsForUser(Authentication authentication){
        return profileService.getSubsForUser(authentication);
    }

    @PostMapping("/createSub")
    @PreAuthorize("isAuthenticated()")
    public void createSub(@RequestBody SubRequest subRequest, Authentication authentication){
        profileService.createSubForUser(subRequest, authentication);
    }

    @DeleteMapping("/deleteSubForUser/{subId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteSub(@PathVariable("subId") long id, Authentication authentication){
        profileService.deleteSubForUser(id, authentication);
    }

    @GetMapping("/getSubTypes")
    public List<SubTypeDTO> getSubTypes(){
        return profileService.getSubTypes();
    }

    @GetMapping("/getInfoAboutSub")
    @PreAuthorize("isAuthenticated()")
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
    @JsonView(Views.FriendView.class)
    public UserFriend friendRequest(@RequestBody User user, Authentication authentication){
        return profileService.friendRequest(authentication, user);
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

    @PostMapping("/acceptFriend")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.FriendView.class)
    public UserDTO acceptFriend(@RequestBody UserFriend userFriend, Authentication authentication){
        return profileService.acceptFriend(userFriend, authentication);
    }
    @DeleteMapping("/deniedFriendRequest")
    @PreAuthorize("hasRole('USER')")
    public void deniedFriendRequest(@RequestParam(name = "friendRequestId") long userFriendId, Authentication authentication){
        profileService.deniedFriendRequest(userFriendId, authentication);
    }

    @DeleteMapping("/deleteFriend")
    @PreAuthorize("hasRole('USER')")
    public void deleteFriend(@RequestParam(name = "userId") long userId, @RequestParam(name = "friendId") long friendId, Authentication authentication){
        profileService.deleteFriend(userId, friendId, authentication);
    }

    @GetMapping("/sub-money-currency")
    @PreAuthorize("isAuthenticated()")
    @JsonView(Views.MoneyCurrencyView.class)
    public MoneyCurrencyDTO getSubMoneyCurrency(@RequestParam("name") String name, @RequestParam("id") long id){
        return profileService.getSubMoneyCurrency(id, name);
    }
}
