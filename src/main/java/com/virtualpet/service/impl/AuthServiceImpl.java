package com.virtualpet.service.impl;

import com.virtualpet.exeption.user.EmailAlreadyExistException;
import com.virtualpet.exeption.RolesNotFoundException;
import com.virtualpet.exeption.user.UserNotFoundException;
import com.virtualpet.exeption.user.UsernameAlreadyExistException;
import com.virtualpet.model.enums.ERole;
import com.virtualpet.model.Role;
import com.virtualpet.model.User;
import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.payload.response.JwtRefreshResponse;
import com.virtualpet.payload.response.JwtResponse;
import com.virtualpet.repository.RoleRepository;
import com.virtualpet.repository.UserRepository;
import com.virtualpet.security.jwt.JwtUtils;
import com.virtualpet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private static final String USERNAME = "username";

    /**
     * {@inheritDoc}
     */
    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerUser(SignupRequest signupRequest) {
        boolean isExistsByUsername = userRepository.existsByUsername(signupRequest.getUsername());
        if (isExistsByUsername)
            throw new UsernameAlreadyExistException(signupRequest.getUsername());
        boolean isExistsByEmail = userRepository.existsByEmail(signupRequest.getEmail());
        if (isExistsByEmail)
            throw new EmailAlreadyExistException(signupRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encodedPassword);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(RolesNotFoundException::new);
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JwtRefreshResponse refreshToken(HttpServletRequest request) {
        String username = request.getHeader(USERNAME);
        if (username != null) {
            String token = jwtUtils.generateRefreshJwtToken(username);
            return new JwtRefreshResponse(username, token);
        }
        throw new UserNotFoundException();
    }
}
