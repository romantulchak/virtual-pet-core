package com.virtualpet.component;

import com.virtualpet.service.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserAccess {
    public boolean hasUserId(Authentication authentication, Long id){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return user != null && user.getId().equals(id);
    }
}
