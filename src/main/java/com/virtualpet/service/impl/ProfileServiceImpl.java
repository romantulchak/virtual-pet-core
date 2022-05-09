package com.virtualpet.service.impl;

import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.exeption.BadRequestException;
import com.virtualpet.exeption.MaximumNumberOfSubsException;
import com.virtualpet.exeption.sub.SubNotFoundException;
import com.virtualpet.exeption.sub.SubTypeNotFoundException;
import com.virtualpet.exeption.sub.SubWithNameAlreadyExistException;
import com.virtualpet.exeption.user.UserAuthenticationException;
import com.virtualpet.exeption.user.UserFriendNotFoundException;
import com.virtualpet.exeption.user.UserNotFoundException;
import com.virtualpet.model.*;
import com.virtualpet.payload.request.sub.SubRequest;
import com.virtualpet.projection.SubMoneyCurrencyProjection;
import com.virtualpet.repository.*;
import com.virtualpet.service.ProfileService;
import com.virtualpet.transformer.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final SubRepository subRepository;
    private final InventoryRepository inventoryRepository;
    private final SubTypeRepository subTypeRepository;
    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final DressedItemRepository dressedItemRepository;
    private final UserFriendRepository userFriendRepository;
    private final Transformer transformer;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubDTO> getSubsForUser(Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<Sub> subs = subRepository.getAllSubsForUser(user.getId());
        return subs.stream().map(transformer::getSubDTO).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<UserDTO> getFriends(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findUserFriends(userInSystem.getId()).stream().map(transformer::getUserDTO).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<UserFriend> getFriendsRequest(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userInSystem.getId()).orElse(null);
        if (user != null) {
            return userFriendRepository.findAllByUser(user);

        }
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<UserFriend> getFriendsResponse(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userInSystem.getId()).orElse(null);
        if (user != null) {
            return userFriendRepository.findAllByUserRequest(user);
        }
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserFriend friendRequest(Authentication authentication, User user) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User userInSystem = userRepository.findById(userDetails.getId()).orElse(null);
        if (userInSystem != null) {
            UserFriend userFriend = new UserFriend(userInSystem, user);
            userFriendRepository.save(userFriend);
            return userFriend;
        }
        throw new UserAuthenticationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUserByUsername(String username, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User userInSystem = userRepository.findById(userDetails.getId()).orElseThrow(() -> new UserNotFoundException(userDetails.getUsername()));
        User user = userRepository.findByUsername(username)
                .filter(x -> !x.getUsername().equals(userDetails.getUsername())).
                orElseThrow(() -> new UserNotFoundException(username));
        return new UserDTO(user, userInSystem);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void createSubForUser(SubRequest subRequest, Authentication authentication) {
        if (subRequest != null) {
            if (!subRepository.existsByName(subRequest.getName())) {
                UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
                User user = userRepository.findById(userInSystem.getId()).orElseThrow(UserNotFoundException::new);
                if (subRepository.countSubByUser(user) <= user.getMaxNumberOfSubs()) {
                    SubType subType = subTypeRepository.findById(subRequest.getId()).
                            orElseThrow(() -> new SubTypeNotFoundException(subRequest.getName()));
                    Inventory inventory = new Inventory();
                    Level level = new Level();
                    DressedItem dressedItem = new DressedItem();
                    dressedItemRepository.save(dressedItem);
                    levelRepository.save(level);
                    inventoryRepository.save(inventory);
                    Sub sub = new Sub()
                            .setName(subRequest.getName())
                            .setSubType(subType)
                            .setInventory(inventory)
                            .setLevel(level)
                            .setDressedItems(dressedItem)
                            .setAttack(subType.getAttack())
                            .setDefence(subType.getDefence())
                            .setDefence(subType.getDefence())
                            .setHealth(subType.getHealth())
                            .setUser(user)
                            .setModelPath(subType.getModelPath())
                            .setIconPath(subType.getIconPath());
                    subRepository.save(sub);
                } else {
                    throw new MaximumNumberOfSubsException();
                }
            } else {
                throw new SubWithNameAlreadyExistException(subRequest.getName());
            }
        } else {
            throw new BadRequestException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSubForUser(long id, Authentication authentication) {
        Sub sub = subRepository.findById(id).orElseThrow(() -> new SubNotFoundException(id));
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if (sub != null && user.getId().equals(sub.getUser().getId())) {
            subRepository.delete(sub);
        } else {
            throw new BadRequestException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubDTO chooseSub(long subId, long userId, Authentication authentication) {
        Sub sub = subRepository.findById(subId).orElse(null);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if (sub != null && user.getId().equals(sub.getUser().getId())) {
            return transformer.getSubDTO(sub);
        }
        throw new SubNotFoundException(subId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubTypeDTO> getSubTypes() {
        List<SubType> subTypes = subTypeRepository.findAll();
        return subTypes.stream().map(transformer::getSubTypeDTO).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO acceptFriend(UserFriend userFriend, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User userInSystem = userRepository.findById(userDetails.getId()).orElseThrow(() -> new UserNotFoundException(userDetails.getUsername()));
        User user = userRepository.findById(userFriend.getUser().getId()).orElseThrow(() -> new UserNotFoundException(userFriend.getUser().getUsername()));
        userInSystem.getFriends().add(userFriend.getUser());
        userInSystem.setFriends(userInSystem.getFriends());
        user.getFriends().add(userInSystem);
        user.setFriends(user.getFriends());
        userRepository.save(userInSystem);
        userRepository.save(user);
        userFriendRepository.delete(userFriend);
        return transformer.getUserDTO(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deniedFriendRequest(long friendRequestId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserFriend userFriend = userFriendRepository.findById(friendRequestId).orElseThrow(() -> new UserFriendNotFoundException(friendRequestId));
        if (userDetails.getId() == userFriend.getUserRequest().getId() || userDetails.getId() == userFriend.getUser().getId()) {
            userFriendRepository.delete(userFriend);
        } else {
            throw new UserAuthenticationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteFriend(long userId, long friendId, Authentication authentication) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (user.getId() == userDetails.getId()) {
            User friend = userRepository.findById(friendId).orElseThrow(UserNotFoundException::new);
            user.getFriends().remove(friend);
            friend.getFriends().remove(user);
            userRepository.save(user);
            userRepository.save(friend);
        } else {
            throw new UserAuthenticationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyCurrencyDTO getSubMoneyCurrency(long id, String name) {
        SubMoneyCurrencyProjection projection = subRepository.findByIdAndName(id, name).orElseThrow(SubNotFoundException::new);
        return new MoneyCurrencyDTO(projection.getMoney(), projection.getCurrency());
    }
}
