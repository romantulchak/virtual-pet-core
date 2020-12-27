package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Views;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.payload.response.SubResponse;
import com.virtualpet.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        SubDTO subDTO = gameService.upMoneyLevel(subRequest);
        return new ResponseEntity<>(new SubResponse<SubDTO>(subDTO, "Ok"), HttpStatus.OK);
    }

    @PutMapping("/saveMoney/{money}")
    @JsonView(Views.SubView.class)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveMoney(@RequestBody SubRequest subRequest, @PathVariable("money") long money){
        SubDTO sub = gameService.saveMoney(subRequest, money);
        return new ResponseEntity<>(new SubResponse<SubDTO>(sub, "Ok"), HttpStatus.OK);
    }

    @GetMapping("/getBoss/{subId}")
    public BossLevelDTO getBoss(@PathVariable("subId") long subId){
        return gameService.getBoss(subId);
    }

    @PutMapping("/upSubAttack")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> upSubAttack(@RequestBody SubRequest subRequest){
        SubDTO subDTO = gameService.upSubAttack(subRequest);
        return new ResponseEntity<>(new SubResponse<SubDTO>(subDTO, "Ok"), HttpStatus.OK);
    }
}
