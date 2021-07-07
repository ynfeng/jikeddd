package com.github.ynfeng.jikeddd.domain.usercontext;

import com.github.ynfeng.jikeddd.domain.Persistentable;
import javax.persistence.Entity;

@Entity
public class User extends Persistentable {
    private String name;
    private Long id;

    public User(String name) {
        this.name = name;
    }

    protected User() {

    }

    public String name() {
        return name;
    }
}
