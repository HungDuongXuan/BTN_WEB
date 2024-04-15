package com.dzungyb.btn_web.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
    @Size(min = 6, max = 20, message = "Invalid username length")
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    private Boolean gender;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String roles;
}
