package com.virtualpet.services.impl;

import com.virtualpet.components.UserAccess;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private SubRepository subRepository;

    @Autowired
    public GameServiceImpl(SubRepository subRepository){
        this.subRepository = subRepository;
    }

    @Override
    public ResponseEntity<?> moneyByClick(SubRequest subRequest, int clicks) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            sub.setMoney(sub.getMoney() + sub.getMoneyMultiplier());
            subRepository.save(sub);
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> upMoneyLevel(SubRequest subRequest) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            double price = sub.getMoneyUpPrice() * Math.pow(1.07, sub.getMoneyUpLevel());
            if(price <= sub.getMoney()) {
                sub.setMoneyUpPrice(price);
                sub.setMoney(sub.getMoney() - price);
                sub.setMoneyUpLevel(sub.getMoneyUpLevel() + 1);
                sub.setMoneyMultiplier(5 * sub.getMoneyUpLevel());
                subRepository.save(sub);
                return new ResponseEntity<>("Ok", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("You don't have enough money", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }
}
