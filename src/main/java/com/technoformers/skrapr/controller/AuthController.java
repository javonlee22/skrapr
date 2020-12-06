package com.technoformers.skrapr.controller;

import com.technoformers.skrapr.entity.User;
import com.technoformers.skrapr.request.LoginRequest;
import com.technoformers.skrapr.response.LoginResponse;
import com.technoformers.skrapr.security.userdetails.SkaprUserDetails;
import com.technoformers.skrapr.security.userdetails.SkaprUserDetailsService;
import com.technoformers.skrapr.service.UserService;
import com.technoformers.skrapr.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkaprUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(),body.getPassword()));
        } catch(BadCredentialsException e) {
            return new ResponseEntity<>(
                    new LoginResponse(null, null, "Invalid username or password"),
                    HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        final SkaprUserDetails customUserDetails = (SkaprUserDetails) userDetails;
        final User user = customUserDetails.getUser();
        return ResponseEntity.ok(new LoginResponse(user, jwt, "Success"));
    }
}
