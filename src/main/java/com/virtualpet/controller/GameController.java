package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.BossLevelDTO;
import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Views;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.service.impl.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameServiceImpl gameService;

    @PutMapping("/up-moeny-level")
    @JsonView(Views.MoneyCurrencyView.class)
    @PreAuthorize("isAuthenticated()")
    public MoneyCurrencyDTO upMoneyLevel(@RequestBody SubRequest subRequest) {
        return gameService.upMoneyLevel(subRequest);
    }

    @PutMapping("/save-money/{money}")
    @JsonView(Views.SubView.class)
    @PreAuthorize("isAuthenticated()")
    public void saveMoney(@RequestBody SubRequest subRequest, @PathVariable("money") long money) {
        gameService.saveMoney(subRequest, money);
    }

    @GetMapping("/boss/{subId}")
    public BossLevelDTO getBoss(@PathVariable("subId") long subId) {
        return gameService.getBoss(subId);
    }

    @PutMapping("/up-attack")
    @JsonView(Views.GameSubView.class)
    @PreAuthorize("isAuthenticated()")
    public SubDTO upSubAttack(@RequestBody SubRequest subRequest) {
        return gameService.upSubAttack(subRequest);
    }
}
