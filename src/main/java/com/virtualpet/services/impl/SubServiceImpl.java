package com.virtualpet.services.impl;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import com.virtualpet.models.SubType;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.repos.SubTypeRepository;
import com.virtualpet.services.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubServiceImpl implements SubService {

    private SubTypeRepository subTypeRepository;

    @Autowired
    public SubServiceImpl(SubTypeRepository subTypeRepository){
        this.subTypeRepository = subTypeRepository;
    }


    @Override
    public ResponseEntity<?> createSub(SubTypeDTO subDTO) {
        if(subDTO != null){
            subTypeRepository.save(new SubType(subDTO.getAttack(), subDTO.getDefence(), subDTO.getModelPath(), subDTO.getIconPath()));
            return new ResponseEntity<>(new MessageResponse("Ok"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageResponse("Bad"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteSub(Long id) {
        SubType subType = subTypeRepository.findById(id).orElse(null);
        if(subType != null){
            subTypeRepository.delete(subType);
            return new ResponseEntity<>(new MessageResponse("Sub was deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("Sub not found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> editSub() {
        return null;
    }


}
