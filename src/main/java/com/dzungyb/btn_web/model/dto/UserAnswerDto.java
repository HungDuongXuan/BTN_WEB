package com.dzungyb.btn_web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerDto {
    private int idContest;
    private List<AnswerQuestionDto> answerQuestion;
}
