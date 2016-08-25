package com.logzc.webzic.orm;

import com.logzc.webzic.orm.field.Column;
import com.logzc.webzic.orm.table.Table;

import java.util.Date;

/**
 * Created by lishuang on 2016/8/22.
 */
@Table(name = "accounts")
public class Account {

    @Column(id = true)
    private int id;

    @Column
    private String name;

    @Column
    private boolean deleted;

    @Column
    private int age;

    @Column
    private String password;

    @Column
    private float weight;

    @Column
    private double height;

    @Column
    private Date time;


    public Account(){

    }

    public Account(int id, String name, boolean deleted, int age, String password, float weight, double height, Date time) {
        this.id = id;
        this.name = name;
        this.deleted=deleted;
        this.age = age;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.time = time;
    }

    public void setAge(int age){
        this.age=age;
    }

}
