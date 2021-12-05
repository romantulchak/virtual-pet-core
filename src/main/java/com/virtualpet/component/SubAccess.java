package com.virtualpet.component;

import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubAccess {

    private final SubRepository subRepository;

    public boolean hasAccess(long subId, Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<Long> userSubsId = subRepository.findUserSubsId(user.getId());
        return userSubsId.contains(subId);
    }

}
