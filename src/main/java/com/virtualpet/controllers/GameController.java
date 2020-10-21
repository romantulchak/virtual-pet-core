package com.virtualpet.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.components.UserAccess;
import com.virtualpet.dtos.BossLevelDTO;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Boss;
import com.virtualpet.models.Level;
import com.virtualpet.models.Views;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.services.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/game")
public class GameController {

    private GameServiceImpl gameService;
    @Autowired
    public GameController(GameServiceImpl gameService){
        this.gameService = gameService;
    }


    @PutMapping("/upMoneyLevel")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> upMoneyLevel(@RequestBody SubRequest subRequest){
        return gameService.upMoneyLevel(subRequest);
    }

    @PutMapping("/saveMoney/{money}")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> saveMoney(@RequestBody SubRequest subRequest, @PathVariable("money") long money){
        return gameService.saveMoney(subRequest, money);
    }

    @GetMapping("/getBoss/{subId}")
    public BossLevelDTO getBoss(@PathVariable("subId") long subId){
        return gameService.getBoss(subId);
    }

    @PutMapping("/upSubAttack")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> upSubAttack(@RequestBody SubRequest subRequest){
        return gameService.upSubAttack(subRequest);
    }
}
