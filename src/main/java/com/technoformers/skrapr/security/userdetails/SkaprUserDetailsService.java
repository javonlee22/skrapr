package com.technoformers.skrapr.security.userdetails;

import com.technoformers.skrapr.entity.User;
import com.technoformers.skrapr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SkaprUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userService.getUserByUsername(s);
        if (user == null) throw new UsernameNotFoundException(s);
        return new SkaprUserDetails(user);
    }
}
