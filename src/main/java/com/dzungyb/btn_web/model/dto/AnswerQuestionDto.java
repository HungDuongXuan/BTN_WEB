package com.dzungyb.btn_web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerQuestionDto {
    private int idQuestion;
    private Integer answer;
}
