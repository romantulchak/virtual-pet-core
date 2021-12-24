package com.virtualpet.service.impl;

import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.exeption.LevelNotFoundException;
import com.virtualpet.exeption.sub.SubNotEnoughMoneyException;
import com.virtualpet.exeption.sub.SubNotFoundException;
import com.virtualpet.model.Boss;
import com.virtualpet.model.Level;
import com.virtualpet.model.Sub;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.projection.SubMoneyCurrencyProjection;
import com.virtualpet.repository.BossRepository;
import com.virtualpet.repository.LevelRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.GameService;
import com.virtualpet.transformer.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final SubRepository subRepository;
    private final LevelRepository levelRepository;
    private final BossRepository bossRepository;
    private final Transformer transformer;
    private List<Boss> bosses = new ArrayList<>();
    private Boss currentBoss;

    @Transactional
    @Override
    public MoneyCurrencyDTO upMoneyLevel(SubRequest subRequest) {
        SubMoneyCurrencyProjection projection = subRepository.findByIdAndName(subRequest.getId(), subRequest.getName()).orElseThrow(SubNotFoundException::new);
        Money money = projection.getMoney();
        Currency currency = projection.getCurrency();
        long price = Math.round(money.getMoneyUpPrice() * Math.pow(1.07, money.getMoneyUpLevel()));
        if (money.getMoneyUpPrice() <= currency.getMoney()) {
            currency.setMoney(currency.getMoney() - money.getMoneyUpPrice());
            money.setMoneyUpPrice(price);
            money.setMoneyUpLevel(money.getMoneyUpLevel() + 1);
            money.setMoneyMultiplier(5 * money.getMoneyUpLevel());
            subRepository.updateSubMoneyCurrency(subRequest.getId(), currency, money);
            return transformer.getMoneyCurrencyDTO(money, currency);
        } else {
            throw new SubNotEnoughMoneyException(subRequest.getName(), currency.getMoney(), money.getMoneyUpPrice());
        }
    }

    @Transactional
    @Override
    public void saveMoney(SubRequest subRequest, long money) {
        if (subRepository.existsById(subRequest.getId())){
            subRepository.updateSubPlusCurrency(subRequest.getId(), money);
        }else{
            throw new SubNotFoundException(subRequest.getId());
        }
    }

    @Override
    public BossLevelDTO getBoss(long subId) {
        Sub sub = subRepository.findById(subId).orElse(null);
        if (sub != null) {
            Level level = levelRepository.findById(sub.getLevel().getId()).orElseThrow(LevelNotFoundException::new);
            initBosses(level);
            int randomIndex = getRandomIndex();
            currentBoss = bosses.get(randomIndex);
            bosses.remove(currentBoss);
            if (level.getLevel() > 1) {
                Random random = new Random();
                return new BossLevelDTO(currentBoss, level.getLevel() * random.nextDouble() * (0.90 - 0.45) + 0.45, level.getLevel());
            }
            return new BossLevelDTO(currentBoss, level.getLevel());

        }
        throw new SubNotFoundException(subId);
    }

    @Override
    public SubDTO upSubAttack(SubRequest subRequest) {
        Sub sub = subRepository.findById(subRequest.getId())
                .orElseThrow(() -> new SubNotFoundException(subRequest.getId()));
        long price = Math.round(sub.getSubAttack().getAttackMoneyUp() * Math.pow(1.09, sub.getSubAttack().getAttackUpLevel()));
        if (sub.getSubAttack().getAttackMoneyUp() <= sub.getCurrency().getMoney()) {
            sub.getCurrency().setMoney(sub.getCurrency().getMoney() - sub.getSubAttack().getAttackMoneyUp());
            sub.getSubAttack().setAttackMoneyUp(price);
            sub.getSubAttack().setAttackUpLevel(sub.getSubAttack().getAttackUpLevel() + 1);
            sub.getSubAttack().setAttackMultiplier(5 * sub.getMoney().getMoneyUpLevel());
            sub.setAttack(sub.getAttack() + ((int) (0.6 * sub.getSubAttack().getAttackUpLevel())));
            subRepository.save(sub);
            return transformer.getSubDTO(sub);
        } else {
            throw new SubNotEnoughMoneyException(sub, sub.getSubAttack().getAttackMoneyUp());
        }
    }


    private void initBosses(Level level) {
        if (bosses.isEmpty()) {
            level.setLevel(level.getLevel() + 1);
            levelRepository.save(level);
            bosses.addAll(bossRepository.findAll());
        }
    }

    private int getRandomIndex() {
        Random random = new SecureRandom();
        int bossesSize = bosses.size();
        return random.nextInt(bossesSize);
    }
}
