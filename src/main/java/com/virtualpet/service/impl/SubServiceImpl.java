package com.virtualpet.service.impl;

import com.virtualpet.exeption.sub.SubTypeIsNullException;
import com.virtualpet.exeption.sub.SubTypeWithNameAlreadyExist;
import com.virtualpet.model.SubType;
import com.virtualpet.repository.SubTypeRepository;
import com.virtualpet.service.SubService;
import com.virtualpet.utils.FileSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubServiceImpl implements SubService {

    private final SubTypeRepository subTypeRepository;
    private String filePath;
    @Value("${upload.path}")
    private String path;


    /**
     * {@inheritDoc}
     */
    @Override
    public void createSub(SubType subType) {
        if(subType != null){
            if(!subTypeRepository.existsByName(subType.getName())) {
                subType.setIconPath(filePath);
                subType.setModelPath(filePath);
                subType.setCreatedAt(LocalDateTime.now());
                subTypeRepository.save(subType);
            }else {
                throw new SubTypeWithNameAlreadyExist(subType.getName());
            }
        }else{
            throw new SubTypeIsNullException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSub(Long id) {
        SubType subType = subTypeRepository.findById(id).orElse(null);
        if(subType != null){
            subTypeRepository.delete(subType);
        }else {
            throw new SubTypeIsNullException();
        }
    }

    /**
     * {@inheritDoc}
     */
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
