package com.dzungyb.btn_web.model;

import com.dzungyb.btn_web.entity.Exam;
import com.dzungyb.btn_web.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailModel {
    private int id;
    private String name;
    private String description;
    private Boolean type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int timeDuration;
    List<Question> question;

    public ExamDetailModel(Exam exam, List<Question> question){
        this.id = exam.getId();
        this.name = exam.getName();
        this.description = exam.getDescription();
        this.type = exam.getType();
        this.startTime = exam.getStartTime();
        this.endTime = exam.getEndTime();
        this.timeDuration = exam.getTimeDuration();
        this.question = question;
    }
}
