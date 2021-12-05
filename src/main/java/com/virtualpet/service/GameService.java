package com.virtualpet.service;

import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.payload.request.SubRequest;

public interface GameService {

    MoneyCurrencyDTO upMoneyLevel(SubRequest subRequest);

    SubDTO saveMoney(SubRequest subRequest, long money);

    BossLevelDTO getBoss(long subId);

    SubDTO upSubAttack(SubRequest subRequest);
}
