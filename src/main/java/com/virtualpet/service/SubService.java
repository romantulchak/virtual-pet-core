package com.virtualpet.service;

import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.model.SubType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SubService {
    void createSub(SubType subType);
    void deleteSub(Long id);
    ResponseEntity<?> editSub();
    void uploadFile(MultipartFile multipartFile);

}
