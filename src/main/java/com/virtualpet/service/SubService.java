package com.virtualpet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SubService {

    /**
     * Creates a subtype that will be used to further create subs through users
     *
     * @param subType to be created
     * @param image of subtype class
     */
    void createSub(String subType, MultipartFile image) throws JsonProcessingException;

    /**
     * Deletes from game subtype by its id
     *
     * @param id of subtype to be deleted
     */
    void deleteSub(Long id);

    /**
     * TODO: to be implemented
     */
    ResponseEntity<?> editSub();
}
