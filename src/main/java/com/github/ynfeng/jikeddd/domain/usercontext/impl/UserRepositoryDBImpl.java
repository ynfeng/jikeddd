package com.github.ynfeng.jikeddd.domain.usercontext.impl;

import com.github.ynfeng.jikeddd.domain.usercontext.User;
import com.github.ynfeng.jikeddd.domain.usercontext.UserDuplicatedException;
import com.github.ynfeng.jikeddd.domain.usercontext.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryDBImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryDBImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        Optional<User> existUser = findUserByName(user.name());
        if (existUser.isPresent()) {
            throw new UserDuplicatedException();
        }
        entityManager.persist(user);
    }

    private Optional<User> findUserByName(String name) {
        TypedQuery<User> query = entityManager
            .createQuery("select u from User u where u.name = :name ", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }
}
