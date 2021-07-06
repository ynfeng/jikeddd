package com.github.ynfeng.jikeddd.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persistentable {
    @Id
    @GeneratedValue
    private Long id;

    protected long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
