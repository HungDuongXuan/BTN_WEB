package com.dzungyb.btn_web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class UserDtoOut {
    private int id;
    private String name;
    private Boolean gender;
    private String email;
}