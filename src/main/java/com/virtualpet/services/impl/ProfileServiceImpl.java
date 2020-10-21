package com.virtualpet.services.impl;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import com.virtualpet.models.*;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repos.*;
import com.virtualpet.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private SubRepository subRepository;
    private InventoryRepository inventoryRepository;
    private SubTypeRepository subTypeRepository;
    private UserRepository userRepository;
    private LevelRepository levelRepository;
    @Autowired
    public ProfileServiceImpl(SubRepository subRepository, InventoryRepository inventoryRepository, SubTypeRepository subTypeRepository, UserRepository userRepository, LevelRepository levelRepository){
        this.subRepository = subRepository;
        this.inventoryRepository = inventoryRepository;
        this.subTypeRepository = subTypeRepository;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
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
    public ResponseEntity<?> createSubForUser(SubRequest subRequest, Authentication authentication) {
        if(subRequest != null){
            if(!subRepository.existsByName(subRequest.getName())){
                UserDetailsImpl userInSystem = (UserDetailsImpl) authentication.getPrincipal();
                User user = userRepository.findById(userInSystem.getId()).orElse(null);
                if(user != null && !(subRepository.countSubByUser(user) >= user.getMaxNumberOfSubs()) ) {

                    SubType subType = subTypeRepository.findById(subRequest.getSubId()).orElseThrow(VerifyError::new);
                    Inventory inventory = new Inventory();
                    Level level = new Level();
                    levelRepository.save(level);
                    inventoryRepository.save(inventory);
                    Sub sub = new Sub(subRequest.getName(), subType.getAttack(), inventory, subType.getDefence(), user, subType, subType.getModelPath(), subType.getIconPath(), level, new SubAttack());
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
    public ResponseEntity<?> deleteSubForUser(SubRequest subRequest, Authentication authentication) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
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
}
