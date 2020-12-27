package com.virtualpet.service.impl;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.exeption.UserNotFoundException;
import com.virtualpet.model.*;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repository.*;
import com.virtualpet.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private SubRepository subRepository;
    private InventoryRepository inventoryRepository;
    private SubTypeRepository subTypeRepository;
    private UserRepository userRepository;
    private LevelRepository levelRepository;
    private DressedItemRepository dressedItemRepository;
    private UserFriendRepository userFriendRepository;
    @Autowired
    public ProfileServiceImpl(SubRepository subRepository, InventoryRepository inventoryRepository, SubTypeRepository subTypeRepository, UserRepository userRepository, LevelRepository levelRepository, DressedItemRepository dressedItemRepository, UserFriendRepository userFriendRepository){
        this.subRepository = subRepository;
        this.inventoryRepository = inventoryRepository;
        this.subTypeRepository = subTypeRepository;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
        this.dressedItemRepository = dressedItemRepository;
        this.userFriendRepository = userFriendRepository;
    }


    @Override
    public List<SubDTO> getSubsForUser(Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<Sub> subs = subRepository.getAllSubsForUser(user.getId());
        return subs.stream().map(this::subToSubDTO).collect(Collectors.toList());
    }

    private SubDTO subToSubDTO(Sub sub){
        return new SubDTO(sub);
    }

    @Override
    public Set<UserDTO> getFriends(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userInSystem.getId()).orElse(null);
        return user.getUsers().stream().map(x->convertUserToDTO(x)).collect(Collectors.toSet());
    }

    @Override
    public Set<UserFriend> getFriendsRequest(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userInSystem.getId()).orElse(null);
        if(user != null){
          return userFriendRepository.findAllByUser(user);

        }
        return null;
    }

    @Override
    public Set<UserFriend> getFriendsResponse(Authentication authentication) {
        UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userInSystem.getId()).orElse(null);
        if(user != null){
            System.out.println(user.getId());
            return userFriendRepository.findAllByUserRequest(user);

        }
        return null;
    }

    @Override
    public boolean friendRequest(Authentication authentication, User user) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User userInSystem = userRepository.findById(userDetails.getId()).orElse(null);
        if(userInSystem != null){
            UserFriend userFriend = new UserFriend(userInSystem, user);
            userFriendRepository.save(userFriend);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserByUsername(String username, Authentication authentication) {
        UserDetailsImpl userDetails =(UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findByUsername(username).filter(x->!x.getUsername().equals(userDetails.getUsername())).orElseThrow(()->new UserNotFoundException(username));
        if (user != null)
            return convertUserToDTO(user);
        return null;
    }

    @Override
    public ResponseEntity<?> createSubForUser(SubRequest subRequest, Authentication authentication) {
        if(subRequest != null){
            if(!subRepository.existsByName(subRequest.getName())){
                UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
                User user = userRepository.findById(userInSystem.getId()).orElse(null);
                if(user != null && !(subRepository.countSubByUser(user) >= user.getMaxNumberOfSubs()) ) {

                    SubType subType = subTypeRepository.findById(subRequest.getSubId()).orElseThrow(VerifyError::new);
                    Inventory inventory = new Inventory();
                    Level level = new Level();
                    DressedItem dressedItem = new DressedItem();
                    dressedItemRepository.save(dressedItem);
                    levelRepository.save(level);
                    inventoryRepository.save(inventory);
                    Sub sub = new Sub(subRequest.getName(), subType.getAttack(), inventory, subType.getDefence(), user, subType, subType.getModelPath(), subType.getIconPath(), level, new SubAttack(), subType.getHealth(), new Currency(), dressedItem);
                    subRepository.save(sub);
                    return new ResponseEntity<>(new MessageResponse("Ok"), HttpStatus.OK);
                }else
                    return new ResponseEntity<>(new MessageResponse("You already have the maximum number of Sub in your account"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new MessageResponse("Hero with the same name already exist"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Bad"), HttpStatus.BAD_GATEWAY);
    }

    @Override
    public ResponseEntity<?> deleteSubForUser(long id, Authentication authentication) {
        Sub sub = subRepository.findById(id).orElse(null);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if(sub != null && user.getId().equals(sub.getUser().getId())){
            subRepository.delete(sub);
            return new ResponseEntity<>(new MessageResponse("Sub " + sub.getName() + " was removed"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new MessageResponse("Something wrong"), HttpStatus.OK);
        }
    }

    @Override
    public SubDTO chooseSub(long heroId, long userId, Authentication authentication) {
        Sub sub = subRepository.findById(heroId).orElse(null);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if(sub != null && user.getId().equals(sub.getUser().getId())){
            return subToSubDTO(sub);
        }
        return null;
    }

    @Override
    public List<SubTypeDTO> getSubTypes() {
        List<SubType> subTypes = subTypeRepository.findAll();
        return subTypes.stream().map(this::convertSubTypeToDTO).collect(Collectors.toList());

    }

    private SubTypeDTO convertSubTypeToDTO(SubType subType){
        return new SubTypeDTO(subType);
    }
    private UserDTO convertUserToDTO(User user){
        return new UserDTO(user);
    }
}
