package com.virtualpet.services;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface GameService {

    ResponseEntity<?> upMoneyLevel(SubRequest subRequest);

    ResponseEntity<?> saveMoney(SubRequest subRequest, long money);
}
