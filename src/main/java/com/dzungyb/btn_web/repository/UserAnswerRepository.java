package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
    boolean existsById(int id);

    UserAnswer findFirstById(int id);

    List<UserAnswer> findByIdContest(int id);

    List<UserAnswer> findByIdQuestion(int id);
}
