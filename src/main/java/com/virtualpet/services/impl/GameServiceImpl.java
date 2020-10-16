package com.virtualpet.services.impl;

import com.virtualpet.components.UserAccess;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.payload.response.SubResponse;
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
    public ResponseEntity<?> upMoneyLevel(SubRequest subRequest) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            long price = Math.round(sub.getMoneyUpPrice() * Math.pow(1.07, sub.getMoneyUpLevel()));
            System.out.println(price);
            System.out.println(sub.getMoney());
            if(sub.getMoneyUpPrice() <= sub.getMoney()) {
                sub.setMoney(sub.getMoney() - sub.getMoneyUpPrice());
                sub.setMoneyUpPrice(price);
                sub.setMoneyUpLevel(sub.getMoneyUpLevel() + 1);
                sub.setMoneyMultiplier(5 * sub.getMoneyUpLevel());
                subRepository.save(sub);
                return new ResponseEntity<>(new SubResponse(new SubDTO(sub), "Ok", HttpStatus.OK), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new SubResponse(new SubDTO(sub), "You don't have enough money",HttpStatus.BAD_REQUEST), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( new SubResponse(null,"Something wrong", HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveMoney(SubRequest subRequest, long money) {
        Sub sub = subRepository.findById(subRequest.getSubId()).orElse(null);
        if(sub != null){
            sub.setMoney(sub.getMoney() + money);
            subRepository.save(sub);
            return new ResponseEntity<>(new SubResponse(new SubDTO(sub), "Ok", HttpStatus.OK), HttpStatus.OK);
        }

        return new ResponseEntity<>(new SubResponse(null, "Something wrong", HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }
}
