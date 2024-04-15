package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.Contest;
import com.dzungyb.btn_web.entity.ContestUserExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Integer> {
    boolean existsById(int id);

    Contest findFirstById(int id);

    List<Contest> findByIdUser(int id);

    List<Contest> findByIdExam(int id);
}
