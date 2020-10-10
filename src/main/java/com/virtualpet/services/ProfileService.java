package com.virtualpet.services;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.dtos.SubTypeDTO;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProfileService {

    List<SubDTO> getSubsForUser(Authentication authentication);
    ResponseEntity<?> createSubForUser(SubRequest subRequest, Authentication authentication);
    ResponseEntity<?> deleteSubForUser(SubRequest subRequest, Authentication authentication);
    SubDTO chooseSub(long heroId, long userId, Authentication authentication);
    List<SubTypeDTO> getSubTypes();
}
