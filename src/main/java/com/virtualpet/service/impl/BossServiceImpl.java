package com.virtualpet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualpet.constant.AppConstants;
import com.virtualpet.model.Boss;
import com.virtualpet.payload.request.boss.CreateBossRequest;
import com.virtualpet.repository.BossRepository;
import com.virtualpet.repository.ItemRepository;
import com.virtualpet.service.BossService;
import com.virtualpet.utils.FileSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BossServiceImpl implements BossService {

    private final BossRepository bossRepository;
    private final ItemRepository itemRepository;
    @Value("${upload.path}")
    private String path;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String createRequestJson, MultipartFile image) throws JsonProcessingException {
        if (createRequestJson != null){
            ObjectMapper objectMapper = new ObjectMapper();
            CreateBossRequest createBossRequest = objectMapper.readValue(createRequestJson, CreateBossRequest.class);
            String imagePath = FileSaver.saveFile(image, path, AppConstants.BOSS_IMAGE_FOLDER);
            Boss boss = new Boss(createBossRequest.getAttack(), createBossRequest.getDefence(), createBossRequest.getHealth(), createBossRequest.getName(), createBossRequest.getDroppedMoney(), imagePath);
            bossRepository.save(boss);
        }
    }
}
