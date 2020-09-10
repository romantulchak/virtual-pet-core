package com.virtualpet.components;

import com.virtualpet.services.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserAccess {
    public boolean hasUserId(Authentication authentication, Long id){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return user != null && user.getId().equals(id);
    }
}
