package com.virtualpet.service.impl;

import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.exeption.LevelNotFoundException;
import com.virtualpet.exeption.SubNotEnoughMoneyException;
import com.virtualpet.exeption.SubNotFoundException;
import com.virtualpet.model.Boss;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.Level;
import com.virtualpet.model.Sub;
import com.virtualpet.model.sub.Money;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.projection.SubMoneyProjection;
import com.virtualpet.repository.BossRepository;
import com.virtualpet.repository.LevelRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private SubRepository subRepository;
    private LevelRepository levelRepository;
    private BossRepository bossRepository;
    private List<Boss> bosses = new ArrayList<>();
    private Boss currentBoss;

    @Autowired
    public GameServiceImpl(SubRepository subRepository, LevelRepository levelRepository, BossRepository bossRepository) {
        this.subRepository = subRepository;
        this.levelRepository = levelRepository;
        this.bossRepository = bossRepository;
        this.bosses.addAll(bossRepository.findAll());
        if (this.bosses.isEmpty()) {
            createBosses();
        }
    }

    @Transactional
    @Override
    public MoneyCurrencyDTO upMoneyLevel(SubRequest subRequest) {
        SubMoneyProjection projection = subRepository.findByIdAndName(subRequest.getSubId(), subRequest.getName()).orElseThrow(SubNotFoundException::new);
        Money money = projection.getMoney();
        Currency currency = projection.getCurrency();
        long price = Math.round(money.getMoneyUpPrice() * Math.pow(1.07, money.getMoneyUpLevel()));
        if (money.getMoneyUpPrice() <= currency.getMoney()) {
            currency.setMoney(currency.getMoney() - money.getMoneyUpPrice());
            money.setMoneyUpPrice(price);
            money.setMoneyUpLevel(money.getMoneyUpLevel() + 1);
            money.setMoneyMultiplier(5 * money.getMoneyUpLevel());
            subRepository.updateSubMoney(subRequest.getSubId(), currency, money);
            return new MoneyCurrencyDTO(money, currency);
        } else {
            throw new SubNotEnoughMoneyException(subRequest.getName(), currency.getMoney(), money.getMoneyUpPrice());
        }
    }

    @Override
    public SubDTO saveMoney(SubRequest subRequest, long money) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(subRequest.getSubId()));
        sub.getCurrency().setMoney(sub.getCurrency().getMoney() + money);
        subRepository.save(sub);
        return new SubDTO(sub);
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
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if (sub != null) {
            long price = Math.round(sub.getSubAttack().getAttackMoneyUp() * Math.pow(1.09, sub.getSubAttack().getAttackUpLevel()));
            if (sub.getSubAttack().getAttackMoneyUp() <= sub.getCurrency().getMoney()) {
                sub.getCurrency().setMoney(sub.getCurrency().getMoney() - sub.getSubAttack().getAttackMoneyUp());
                sub.getSubAttack().setAttackMoneyUp(price);
                sub.getSubAttack().setAttackUpLevel(sub.getSubAttack().getAttackUpLevel() + 1);
                sub.getSubAttack().setAttackMultiplier(5 * sub.getMoney().getMoneyUpLevel());
                sub.setAttack(sub.getAttack() + ((int) (0.6 * sub.getSubAttack().getAttackUpLevel())));
                subRepository.save(sub);
                return new SubDTO(sub);
            } else {
                throw new SubNotEnoughMoneyException(sub, sub.getSubAttack().getAttackMoneyUp());
            }
        }
        throw new SubNotFoundException(subRequest.getSubId());

    }


    private void initBosses(Level level) {
        if (bosses.isEmpty()) {
            level.setLevel(level.getLevel() + 1);
            levelRepository.save(level);
            bosses.addAll(bossRepository.findAll());
        }
    }

    private int getRandomIndex() {
        Random random = new Random();
        int bossesSize = bosses.size();
        return random.nextInt(bossesSize);
    }

    private void createBosses() {
        List<Boss> bosses = new ArrayList<>(6) {{
            add(new Boss("Dragon 1", 23, 53, "a", "a", 564));
            add(new Boss("Dragon 2", 432, 532, "a", "a", 1364));
            add(new Boss("Dragon 3", 2123, 433, "a", "a", 2364));
            add(new Boss("Dragon 4", 223, 213, "a", "a", 3367));
            add(new Boss("Dragon 5", 133, 533, "a", "a", 1534));
            add(new Boss("Dragon 6", 423, 643, "a", "a", 5623));
        }};
        bossRepository.saveAll(bosses);
        this.bosses.addAll(bosses);
    }
}
