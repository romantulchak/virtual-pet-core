package com.virtualpet.services;

import com.virtualpet.payload.request.SubRequest;
import org.springframework.http.ResponseEntity;

public interface ProfileService {


    ResponseEntity<?> createSub(SubRequest subRequest);

}
