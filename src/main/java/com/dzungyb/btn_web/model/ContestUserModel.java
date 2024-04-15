package com.dzungyb.btn_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestUserModel {
    private int idUser;
    private String name;
    private Double score;
    private Long rightAnswer;
    private Long wrongAnswer;
    private Long blankAnswer;
    private Long finishExam;
    private Long totalExam;
    public ContestUserModel(Object[] obj){
        this.idUser = (int) obj[0];
        this.name = (String) obj[1];
        this.score = (Double) obj[2];
        this.rightAnswer = (Long) obj[3];
        this.wrongAnswer = (Long) obj[4];
        this.blankAnswer = (Long) obj[5];
        this.finishExam = (Long) obj[6];
        this.totalExam = (Long) obj[7];
    }
}
