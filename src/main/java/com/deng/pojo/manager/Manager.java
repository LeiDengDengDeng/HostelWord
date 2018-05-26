package com.deng.pojo.manager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dell on 1/26 0026.
 */
@Entity(name = "manager")
public class Manager {
    private long id;
    private String password;
    private long money;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "money")
    public long getMoney() {
        return money;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(long money) {
        this.money = money;
    }

}
