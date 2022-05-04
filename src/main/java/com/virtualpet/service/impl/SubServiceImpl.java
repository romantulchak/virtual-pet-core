package com.virtualpet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualpet.constant.AppConstants;
import com.virtualpet.exeption.sub.SubTypeIsNullException;
import com.virtualpet.exeption.sub.SubTypeWithNameAlreadyExist;
import com.virtualpet.model.SubType;
import com.virtualpet.payload.request.sub.SubTypeRequest;
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
    @Value("${upload.path}")
    private String path;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createSub(String subTypeJson, MultipartFile image) throws JsonProcessingException {
        if (subTypeJson != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            SubTypeRequest subTypeRequest = objectMapper.readValue(subTypeJson, SubTypeRequest.class);
            if (!subTypeRepository.existsByName(subTypeRequest.getName())) {
                String filePath = FileSaver.saveFile(image, path, AppConstants.SUB_TYPE_IMAGE_FOLDER);
                SubType subType = new SubType()
                        .setName(subTypeRequest.getName())
                        .setCreatedAt(LocalDateTime.now())
                        .setAttack(subTypeRequest.getAttack())
                        .setDefence(subTypeRequest.getDefence())
                        .setHealth(subTypeRequest.getHealth())
                        .setIconPath(filePath)
                        .setModelPath(filePath);
                subTypeRepository.save(subType);
            } else {
                throw new SubTypeWithNameAlreadyExist(subTypeRequest.getName());
            }
        } else {
            throw new SubTypeIsNullException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSub(Long id) {
        SubType subType = subTypeRepository.findById(id).orElse(null);
        if (subType != null) {
            subTypeRepository.delete(subType);
        } else {
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
}
