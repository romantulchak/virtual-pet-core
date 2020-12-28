package com.virtualpet.service;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.model.User;
import com.virtualpet.model.UserFriend;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface ProfileService {

    List<SubDTO> getSubsForUser(Authentication authentication);
    void createSubForUser(SubRequest subRequest, Authentication authentication);
    void deleteSubForUser(long id, Authentication authentication);
    SubDTO chooseSub(long heroId, long userId, Authentication authentication);
    List<SubTypeDTO> getSubTypes();
    Set<UserDTO> getFriends(Authentication authentication);
    UserFriend friendRequest(Authentication authentication, User user);

    UserDTO getUserByUsername(String username, Authentication authentication);
    Set<UserFriend> getFriendsRequest(Authentication authentication);
    Set<UserFriend> getFriendsResponse(Authentication authentication);
    UserDTO acceptFriend(UserFriend userFriend, Authentication authentication);
    void deniedFriendRequest(long friendRequestId, Authentication authentication);
    void deleteFriend(long userId, long friendId, Authentication authentication);
}
