package com.dizhongdi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:User
 * Package:com.dizhongdi.pojo
 * Description:
 *
 * @Date: 2021/8/1 15:03
 * @Author:dizhongdi
 */
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
    }

}
