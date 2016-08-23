package com.logzc.webzic.orm;

import com.logzc.webzic.orm.field.Column;
import com.logzc.webzic.orm.table.Table;

/**
 * Created by lishuang on 2016/8/22.
 */
@Table(name = "accounts")
public class Account {

    @Column(id = true)
    private String name;

    @Column
    private String password;

    // ORMLite needs a no-arg constructor
    public Account() {

    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
