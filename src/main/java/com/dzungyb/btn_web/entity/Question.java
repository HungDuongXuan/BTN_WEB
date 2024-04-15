package com.dzungyb.btn_web.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "id_exam", nullable = false)
    int idExam;

    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "answer1", nullable = false)
    String answer1;

    @Column(name = "answer2", nullable = false)
    String answer2;

    @Column(name = "answer3", nullable = false)
    String answer3;

    @Column(name = "answer4", nullable = false)
    String answer4;

    @Column(name = "right_answer", nullable = false)
    int rightAnswer;

    @Column(name = "explains")
    String explain;
}
