package com.dzungyb.btn_web.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class UserOut {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "name", nullable = false, length = 40)
    String name;

    @Column(name = "gender", nullable = false)
    Boolean gender;

    @Column(name = "username", nullable = false, length = 40)
    String username;

    @Column(name = "email", nullable = false, length = 50)
    String email;
}
