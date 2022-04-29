package com.virtualpet.service;

import com.virtualpet.model.SubType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SubService {

    /**
     * Creates a subtype that will be used to further create subs through users
     *
     * @param subType to be created
     */
    void createSub(SubType subType);

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

    @Deprecated(forRemoval = true)
    /**
     * To be reworked to upload sub image together with creation
     */
    void uploadFile(MultipartFile multipartFile);

}
