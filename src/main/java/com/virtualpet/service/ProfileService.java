package com.virtualpet.service;

import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.model.User;
import com.virtualpet.model.UserFriend;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface ProfileService {

    /**
     * Gets subs for current user in system
     *
     * @param authentication to get user in system
     * @return subs for user in system
     */
    List<SubDTO> getSubsForUser(Authentication authentication);

    /**
     * Creates sub for user in system max number of subs is 5
     *
     * @param subRequest to get sub name
     * @param authentication to get user in system
     */
    void createSubForUser(SubRequest subRequest, Authentication authentication);

    /**
     * Deletes sub for user by sub id. Sub cannot be deleted
     * if it's first sub for user
     *
     * @param id to get sub form DB
     * @param authentication to get user in system
     */
    void deleteSubForUser(long id, Authentication authentication);

    /**
     * Select sub by its id and return his characteristics
     *
     * @param heroId to get sub by id
     * @param userId to check if sub is belongs to current user
     * @param authentication to get user in system
     * @return sub by id
     */
    SubDTO chooseSub(long heroId, long userId, Authentication authentication);

    /**
     * Gets all subtypes in system
     *
     * @return subtypes in system
     */
    List<SubTypeDTO> getSubTypes();

    /**
     * Gets friends for user in system
     *
     * @param authentication to get user in system
     * @return friends for user
     */
    Set<UserDTO> getFriends(Authentication authentication);

    /**
     * Send friend request to user
     * @param authentication to get user in system
     * @param user to be added to friend list request
     * @return UserFriend request
     */
    UserFriend friendRequest(Authentication authentication, User user);

    /**
     * Checks if user is the same as funded by username
     * and return its data
     *
     * @param username to get user from system
     * @param authentication to get user in system and check if username the same
     * @return user data
     */
    UserDTO getUserByUsername(String username, Authentication authentication);

    /**
     * Gets all user requests for user in system
     *
     * @param authentication to get user in system
     * @return all user request for user in system
     */
    Set<UserFriend> getFriendsRequest(Authentication authentication);

    /**
     * Gets all user response for user in system
     * @param authentication to get user in system
     * @return all user response for user in system
     */
    Set<UserFriend> getFriendsResponse(Authentication authentication);

    /**
     * Accept user friend request and return user in system data
     *
     * @param userFriend to get user reuqest
     * @param authentication to get user in system
     * @return accept user request
     */
    UserDTO acceptFriend(UserFriend userFriend, Authentication authentication);

    /**
     * Rejects friend request for user
     *
     * @param friendRequestId to get friend reuqest form DB
     * @param authentication to get user in system
     */
    void deniedFriendRequest(long friendRequestId, Authentication authentication);

    /**
     * Delete friends form friend list
     *
     * @param userId from whom the friend will be deleted
     * @param friendId to find friend to be deleted
     * @param authentication to get user in system
     */
    void deleteFriend(long userId, long friendId, Authentication authentication);

    /**
     * Get currency and money for sub
     *
     * @param id of sub
     * @param name of sub
     * @return money and currency for sub
     */
    MoneyCurrencyDTO getSubMoneyCurrency(long id, String name);
}
