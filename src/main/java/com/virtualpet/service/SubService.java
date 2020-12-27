package com.virtualpet.service;

import com.virtualpet.dto.SubTypeDTO;
import org.springframework.http.ResponseEntity;

public interface SubService {
    ResponseEntity<?> createSub(SubTypeDTO subDTO);
    ResponseEntity<?> deleteSub(Long id);
    ResponseEntity<?> editSub();

}
