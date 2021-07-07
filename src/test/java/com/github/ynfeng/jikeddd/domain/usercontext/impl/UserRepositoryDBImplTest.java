package com.github.ynfeng.jikeddd.domain.usercontext.impl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.ynfeng.jikeddd.domain.usercontext.User;
import com.github.ynfeng.jikeddd.domain.usercontext.UserDuplicatedException;
import com.github.ynfeng.jikeddd.domain.usercontext.UserRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryDBImplTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void should_save_user() {
        User zhangsan = new User("zhangsan");

        userRepository.add(zhangsan);

        assertThat(queryUserByUserName("zhangsan"), notNullValue());
    }

    @Test
    void should_not_duplicate() {
        User zhangsan = new User("zhangsan");
        userRepository.add(zhangsan);

        try {
            User otherZhangsan = new User("zhangsan");
            userRepository.add(otherZhangsan);
            fail();
        } catch (UserDuplicatedException igonred) {
        }
    }

    private User queryUserByUserName(String userName) {
        return entityManager
            .createQuery("select u from User u where u.name = :name", User.class)
            .setParameter("name", userName)
            .getSingleResult();
    }
}
