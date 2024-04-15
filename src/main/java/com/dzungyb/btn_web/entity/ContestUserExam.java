package com.dzungyb.btn_web.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "contest")
public class ContestUserExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @OneToOne
    @JoinColumn(name = "id_user")
    UserOut user;

    @OneToOne
    @JoinColumn(name = "id_exam")
    Exam exam;

    @Column(name = "score")
    Float score;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "submit_time")
    LocalDateTime submitTime;

    @Column(name = "right_answer")
    Integer rightAnswer;

    @Column(name = "wrong_answer")
    Integer wrongAnswer;

    @Column(name = "bank_answer")
    Integer blankAnswer;

}
