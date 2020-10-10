package com.virtualpet.controllers;

import com.virtualpet.components.UserAccess;
import com.virtualpet.dtos.SubDTO;
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

    @PostMapping("/click/{numberOfClick}")
    public ResponseEntity<?> click(@RequestBody SubRequest subRequest, @PathVariable("numberOfClick") int clicks){
        return gameService.moneyByClick(subRequest, clicks);
    }

    @PostMapping("/upMoneyLevel")
    public ResponseEntity<?> upMoneyLevel(@RequestBody SubRequest subRequest){
        return gameService.upMoneyLevel(subRequest);
    }

    @PutMapping("/saveMoney/{money}")
    public ResponseEntity<?> saveMoney(@RequestBody SubRequest subRequest, @PathVariable("money") long money){
        return gameService.saveMoney(subRequest, money);
    }


}
