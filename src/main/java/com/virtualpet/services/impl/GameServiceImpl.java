package com.virtualpet.services.impl;

import com.virtualpet.components.UserAccess;
import com.virtualpet.dtos.BossLevelDTO;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Boss;
import com.virtualpet.models.Level;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.payload.response.SubResponse;
import com.virtualpet.repos.BossRepository;
import com.virtualpet.repos.LevelRepository;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

@Service
public class GameServiceImpl implements GameService {

    private SubRepository subRepository;
    private LevelRepository levelRepository;
    private BossRepository bossRepository;
    private List<Boss> bosses = new ArrayList<>();
    private Boss currentBoss;
    @Autowired
    public GameServiceImpl(SubRepository subRepository, LevelRepository levelRepository, BossRepository bossRepository){
        this.subRepository = subRepository;
        this.levelRepository = levelRepository;
        this.bossRepository = bossRepository;
        this.bosses.addAll(bossRepository.findAll());

    }

    @Override
    public ResponseEntity<?> upMoneyLevel(SubRequest subRequest) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            long price = Math.round(sub.getMoneyUpPrice() * Math.pow(1.07, sub.getMoneyUpLevel()));
            if(sub.getMoneyUpPrice() <= sub.getMoney()) {
                sub.setMoney(sub.getMoney() - sub.getMoneyUpPrice());
                sub.setMoneyUpPrice(price);
                sub.setMoneyUpLevel(sub.getMoneyUpLevel() + 1);
                sub.setMoneyMultiplier(5 * sub.getMoneyUpLevel());
                subRepository.save(sub);
                return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Ok", HttpStatus.OK), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "You don't have enough money",HttpStatus.BAD_REQUEST), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( new SubResponse<SubDTO>(null,"Something wrong", HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveMoney(SubRequest subRequest, long money) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            sub.setMoney(sub.getMoney() + money);
            subRepository.save(sub);
            return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Ok", HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new SubResponse<SubDTO>(null, "Something wrong", HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @Override
    public BossLevelDTO getBoss(long subId) {
        Sub sub = subRepository.findById(subId).orElse(null);
         if(sub != null) {
             Level level = levelRepository.findById(sub.getLevel().getId()).orElse(null);
             if(level != null){
                 initBosses(level);
                 int randomIndex = getRandomIndex();
                 currentBoss = bosses.get(randomIndex);
                 bosses.remove(currentBoss);
                    if(level.getLevel() > 1){
                        Random random = new Random();
                        return new BossLevelDTO(currentBoss, level.getLevel() * random.nextDouble() * (0.60 - 0.45) + 0.45, level.getLevel());
                    }
                return new BossLevelDTO(currentBoss, level.getLevel());
            }
         }

        return null;
    }

    @Override
    public ResponseEntity<?> upSubAttack(SubRequest subRequest) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            long price = Math.round(sub.getSubAttack().getAttackMoneyUp() * Math.pow(1.09, sub.getSubAttack().getAttackUpLevel()));
            System.out.println(price);
            if(sub.getSubAttack().getAttackMoneyUp() <= sub.getMoney()){
                sub.setMoney(sub.getMoney() - sub.getSubAttack().getAttackMoneyUp());
                sub.getSubAttack().setAttackMoneyUp(price);
                sub.getSubAttack().setAttackUpLevel(sub.getSubAttack().getAttackUpLevel() + 1);
                sub.getSubAttack().setAttackMultiplier(5 * sub.getMoneyUpLevel());
                sub.setAttack(sub.getAttack() + ((int)(0.6 * sub.getSubAttack().getAttackUpLevel())) );
                subRepository.save(sub);
                return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Ok", HttpStatus.OK), HttpStatus.OK);
            }else {

                return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "You don't have enough money", HttpStatus.OK), HttpStatus.OK);
            }


        }




        return new ResponseEntity<>(new SubResponse<SubDTO>(null, "Sub not found", HttpStatus.OK), HttpStatus.OK);

    }


    private void initBosses(Level level) {
        if(bosses.isEmpty()){
            level.setLevel(level.getLevel()+1);
            levelRepository.save(level);
            bosses.addAll(bossRepository.findAll());
        }
    }

    private int getRandomIndex() {
        Random random = new Random();
        int bossesSize = bosses.size();
        return random.nextInt(bossesSize);
    }
}
