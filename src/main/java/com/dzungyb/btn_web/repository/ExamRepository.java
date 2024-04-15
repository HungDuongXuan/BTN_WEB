package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
    boolean existsById(int id);

    Exam findFirstById(int id);

    Exam findFirstByStartTime(Date startTime);
}
