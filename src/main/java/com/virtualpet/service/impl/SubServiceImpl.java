package com.virtualpet.service.impl;

import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.exeption.SubTypeIsNull;
import com.virtualpet.exeption.SubTypeWithNameAlreadyExist;
import com.virtualpet.model.SubType;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repository.SubTypeRepository;
import com.virtualpet.service.SubService;
import com.virtualpet.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class SubServiceImpl implements SubService {

    private SubTypeRepository subTypeRepository;
    private String filePath;

    @Value("${upload.path}")
    private String path;
    @Autowired
    public SubServiceImpl(SubTypeRepository subTypeRepository){
        this.subTypeRepository = subTypeRepository;
    }


    @Override
    public void createSub(SubType subType) {
        if(subType != null){
            if(!subTypeRepository.existsByName(subType.getName())) {
                subType.setIconPath(filePath);
                subType.setLocalDateTime(LocalDateTime.now());
                subTypeRepository.save(subType);
            }else {
                throw new SubTypeWithNameAlreadyExist(subType.getName());
            }
        }else{
            throw new SubTypeIsNull();
        }

    }

    @Override
    public void deleteSub(Long id) {
        SubType subType = subTypeRepository.findById(id).orElse(null);
        if(subType != null){
            subTypeRepository.delete(subType);
        }else {
            throw new SubTypeIsNull();
        }
    }

    @Override
    public ResponseEntity<?> editSub() {
        return null;
    }

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        if (multipartFile != null){
            filePath = FileSaver.saveFile(multipartFile, path, "subTypeImages");
        }else {
            throw new RuntimeException("File not found");
        }
    }


}
