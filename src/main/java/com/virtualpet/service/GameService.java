package com.virtualpet.service;

import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;

public interface GameService {

    SubDTO upMoneyLevel(SubRequest subRequest);

    SubDTO saveMoney(SubRequest subRequest, long money);

    BossLevelDTO getBoss(long subId);

    SubDTO upSubAttack(SubRequest subRequest);
}
