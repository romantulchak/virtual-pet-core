package com.virtualpet.controller;

import com.virtualpet.exeption.*;
import com.virtualpet.exeption.item.ItemAlreadyBoughtException;
import com.virtualpet.exeption.item.ItemNotFoundException;
import com.virtualpet.exeption.skill.SkillAlreadyBoughtException;
import com.virtualpet.exeption.skill.SkillAlreadyExistException;
import com.virtualpet.exeption.skill.SkillAlreadyInShopException;
import com.virtualpet.exeption.sub.*;
import com.virtualpet.exeption.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AdvisorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> getBody(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler(BossesEmptyException.class)
    public ResponseEntity<Object> handleBossesEmpty(BossesEmptyException ex, WebRequest request){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LevelNotFoundException.class)
    public ResponseEntity<Object> handleLevelNotFound(LevelNotFoundException ex, WebRequest request){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SubNotFoundException.class)
    public ResponseEntity<Object> handleSubNotFound(SubNotFoundException ex, WebRequest request){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SubNotEnoughMoneyException.class)
    public ResponseEntity<Object> handleSubNotEnoughMoneyException(SubNotEnoughMoneyException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExist(UsernameNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExistException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SubTypeNotFoundException.class)
    public ResponseEntity<Object> handleSubtypeNotFound(SubTypeNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SubWithNameAlreadyExistException.class)
    public ResponseEntity<Object> handleSubWithNameAlreadyExistException(SubWithNameAlreadyExistException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MaximumNumberOfSubsException.class)
    public ResponseEntity<Object> handleMaximumNumberOfSubsException(MaximumNumberOfSubsException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Object> handleUserAuthenticationException(UserAuthenticationException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserFriendNotFoundException.class)
    public ResponseEntity<Object> handleUserFriendNotFoundException(UserFriendNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleUserItemNotFoundException(ItemNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RolesNotFoundException.class)
    public ResponseEntity<Object> handleRolesNotFoundException(RolesNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SubTypeIsNullException.class)
    public ResponseEntity<Object> handleSubTypeIsNull(SubTypeIsNullException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SubTypeWithNameAlreadyExist.class)
    public ResponseEntity<Object> handleSubTypeWithNameAlreadyExist(SubTypeWithNameAlreadyExist ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SkillAlreadyExistException.class)
    public ResponseEntity<Object> handleSkillAlreadyExistException(SkillAlreadyExistException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SkillAlreadyInShopException.class)
    public ResponseEntity<Object> handleSkillAlreadyInShopException(SkillAlreadyInShopException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<Object> handleShopNotFoundException(ShopNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<Object> handleInventoryNotFoundException(InventoryNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ItemAlreadyBoughtException.class)
    public ResponseEntity<Object> handleItemAlreadyBoughtException(ItemAlreadyBoughtException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<Object> handleNotEnoughMoneyException(NotEnoughMoneyException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SkillAlreadyBoughtException.class)
    public ResponseEntity<Object> handleSkillAlreadyBoughtException(SkillAlreadyBoughtException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CannotParsePageException.class)
    public ResponseEntity<Object> handleCannotParsePageException(CannotParsePageException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
