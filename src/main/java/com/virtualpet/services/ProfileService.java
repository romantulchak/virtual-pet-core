package com.virtualpet.services;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProfileService {

    List<SubDTO> getSubsForUser(Authentication authentication);
    ResponseEntity<?> createSubForUser(SubRequest subRequest);

}
