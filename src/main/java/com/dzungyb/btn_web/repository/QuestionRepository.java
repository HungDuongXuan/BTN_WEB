package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsById(int id);

    Question findFirstById(int id);

    List<Question> findByIdExam(int id);
}
