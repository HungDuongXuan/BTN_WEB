package com.dzungyb.btn_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "user_answers")
public class UserAnswer {
    @Id
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "id_contest", nullable = false)
    int idContest;

    @Column(name = "id_question", nullable = false)
    int idQuestion;

    @Column(name = "answer")
    Integer answer;
}
