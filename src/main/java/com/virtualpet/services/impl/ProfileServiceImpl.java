package com.virtualpet.services.impl;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import com.virtualpet.models.Inventory;
import com.virtualpet.models.Sub;
import com.virtualpet.models.SubType;
import com.virtualpet.models.User;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repos.InventoryRepository;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.repos.SubTypeRepository;
import com.virtualpet.repos.UserRepository;
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
    @Autowired
    public ProfileServiceImpl(SubRepository subRepository, InventoryRepository inventoryRepository, SubTypeRepository subTypeRepository, UserRepository userRepository){
        this.subRepository = subRepository;
        this.inventoryRepository = inventoryRepository;
        this.subTypeRepository = subTypeRepository;
        this.userRepository = userRepository;
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
                    inventoryRepository.save(inventory);
                    Sub sub = new Sub(subRequest.getName(), subType.getAttack(), inventory, subType.getDefence(), user, subType, subType.getModelPath(), subType.getIconPath());
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
    public SubDTO chooseSub(SubRequest subRequest, Authentication authentication) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
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
