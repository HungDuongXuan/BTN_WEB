package com.dzungyb.btn_web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionDto {
    private String name;
    private String description;
    private Boolean type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int timeDuration;
    private List<QuestionDto> question;
}
