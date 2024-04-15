package com.dzungyb.btn_web.model;

import com.dzungyb.btn_web.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerQuestionModel {
    private Integer userAnswer;
    private Question question;
}
