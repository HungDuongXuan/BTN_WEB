package com.dzungyb.btn_web.model;

import com.dzungyb.btn_web.entity.Contest;
import com.dzungyb.btn_web.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestDetailModel {
    private int id;
    private int idUser;
    private Exam exam;
    private Float score;
    private LocalDateTime startTime;
    private LocalDateTime submitTime;
    private Integer rightAnswer;
    private Integer wrongAnswer;
    private Integer blankAnswer;

    public ContestDetailModel(Contest contest, Exam exam) {
        this.id = contest.getId();
        this.idUser = contest.getIdUser();
        this.score = contest.getScore();
        this.startTime = contest.getStartTime();
        this.submitTime = contest.getSubmitTime();
        this.rightAnswer = contest.getRightAnswer();
        this.wrongAnswer = contest.getWrongAnswer();
        this.blankAnswer = contest.getBlankAnswer();
        this.exam = exam;
    }
}