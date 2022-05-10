package com.virtualpet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

public interface BossService {

    /**
     * Creates boss from request
     *
     * @param createRequestJson {@link com.virtualpet.payload.request.boss.CreateBossRequest}
     */
    void create(String createRequestJson, MultipartFile image) throws JsonProcessingException;
}
