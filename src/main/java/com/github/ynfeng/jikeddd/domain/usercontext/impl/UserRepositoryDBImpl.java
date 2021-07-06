package com.github.ynfeng.jikeddd.domain.usercontext.impl;

import com.github.ynfeng.jikeddd.domain.usercontext.User;
import com.github.ynfeng.jikeddd.domain.usercontext.UserRepository;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryDBImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryDBImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
}
