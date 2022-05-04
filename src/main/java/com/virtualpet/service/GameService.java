package com.virtualpet.service;

import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.payload.request.sub.SubRequest;

public interface GameService {

    /**
     * Updates money level for current sub and increase price for
     * the subsequent updates
     *
     * @param subRequest contains information about current sub
     * @return current number of Currency and Money
     */
    MoneyCurrencyDTO upMoneyLevel(SubRequest subRequest);

    /**
     * Save money for current sub
     *
     * @param subRequest contains information about current sub
     * @param money amount of money earned
     */
    void saveMoney(SubRequest subRequest, long money);

    /**
     * Gets boss for current sub
     *
     * @param subId to get current boss from system with the required level
     * @return Boss from system with level for current sub
     */
    BossLevelDTO getBoss(long subId);

    /**
     * Updates attack for sub and increase price
     *
     * @param subRequest contains information about current and increase price for
     * the subsequent updates
     * @return sub with updated attack and money
     */
    SubDTO upSubAttack(SubRequest subRequest);
}
