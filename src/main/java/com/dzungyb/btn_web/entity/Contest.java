package com.dzungyb.btn_web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "contest")
public class Contest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "id_exam", nullable = false)
    private int idExam;

    @Column(name = "score")
    private Float score;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "submit_time")
    private LocalDateTime submitTime;

    @Column(name = "right_answer")
    private Integer rightAnswer;

    @Column(name = "wrong_answer")
    private Integer wrongAnswer;

    @Column(name = "blank_answer")
    private Integer blankAnswer;
}
