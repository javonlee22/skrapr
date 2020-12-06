package com.technoformers.skrapr.response;

import com.technoformers.skrapr.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private User user;
    private String token;
    private String message;
}
