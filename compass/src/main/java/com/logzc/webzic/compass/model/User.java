package com.logzc.webzic.compass.model;

import com.logzc.webzic.orm.table.Table;
import lombok.Data;

/**
 * Created by lishuang on 2016/10/11.
 */
@Table(name = "cp_user")
@Data
public class User {

    Long id;
    String username;
    String password;
    String gender;
    String comment;

}
