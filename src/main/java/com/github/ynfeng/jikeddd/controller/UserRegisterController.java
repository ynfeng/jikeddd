package com.github.ynfeng.jikeddd.controller;

import com.github.ynfeng.jikeddd.application.UserRegisterAppService;
import com.github.ynfeng.jikeddd.application.UserRegisterRequest;
import com.github.ynfeng.jikeddd.domain.usercontext.UserDuplicatedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {
    private final UserRegisterAppService userRegisterAppService;

    public UserRegisterController(UserRegisterAppService userRegisterAppService) {
        this.userRegisterAppService = userRegisterAppService;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest request) {
        try {
            userRegisterAppService.register(request);
        } catch (UserDuplicatedException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
