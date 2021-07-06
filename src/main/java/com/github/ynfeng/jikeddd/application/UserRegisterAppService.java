package com.github.ynfeng.jikeddd.application;

import com.github.ynfeng.jikeddd.domain.usercontext.User;
import com.github.ynfeng.jikeddd.domain.usercontext.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRegisterAppService {
    private final UserRepository userRepository;

    public UserRegisterAppService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegisterRequest request) {
        User user = new User(request.getUsername());
        userRepository.add(user);
    }

}
