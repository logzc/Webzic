package com.logzc.webzic.orm.mysql;

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

    private String name;

    private boolean deleted;

    private int age;

    private String password;

    private String foodName;

    private float weight;

    private double height;

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
    public void setFoodName(String foodName){
        this.foodName=foodName;
    }

    public String getName(){
        return name;
    }

}
