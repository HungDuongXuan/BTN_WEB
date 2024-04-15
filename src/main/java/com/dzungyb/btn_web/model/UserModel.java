package com.dzungyb.btn_web.model;

import com.dzungyb.btn_web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;
    private String name;
    private Boolean gender;
    private String username;
    private String email;
    private String password;
    private String roles;

    public static UserModel toUsersModel(User userEntity) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);
        return userModel;
    }
}