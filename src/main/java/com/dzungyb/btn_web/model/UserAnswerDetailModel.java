package com.dzungyb.btn_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerDetailModel {
    private int id;
    private int idContest;
    private List<UserAnswerQuestionModel> answerQuestion;
}
