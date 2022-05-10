package com.virtualpet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtualpet.service.BossService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/boss")
@RequiredArgsConstructor
public class BossController {

    private final BossService bossService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@RequestPart("boss") String createBossRequest, @RequestPart("image") MultipartFile image) throws JsonProcessingException {
        bossService.create(createBossRequest, image);
    }
}
