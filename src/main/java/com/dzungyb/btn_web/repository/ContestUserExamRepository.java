package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.ContestUserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestUserExamRepository extends JpaRepository<ContestUserExam, Integer> {
    @Query("SELECT c.user.id, c.user.name, avg(c.score), sum(c.rightAnswer), sum(c.wrongAnswer), sum(c.blankAnswer), count(c.submitTime), count(c.user.id) from ContestUserExam c group by c.user.id")
    List<Object[]> statisticByUser();

    @Query("SELECT c.exam.id, avg(c.score), sum(c.rightAnswer), sum(c.wrongAnswer), sum(c.blankAnswer), count(c.submitTime), count(c.exam.id) from ContestUserExam c group by c.exam.id")
    List<Object[]> statisticByExam();
}
