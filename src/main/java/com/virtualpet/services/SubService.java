package com.virtualpet.services;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import org.springframework.http.ResponseEntity;

public interface SubService {
    ResponseEntity<?> createSub(SubTypeDTO subDTO);
    ResponseEntity<?> deleteSub(Long id);
    ResponseEntity<?> editSub();

}
