package com.dzungyb.btn_web.controller;

import com.dzungyb.btn_web.entity.Exam;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.ExamDetailModel;
import com.dzungyb.btn_web.model.dto.ExamDto;
import com.dzungyb.btn_web.model.dto.ExamQuestionDto;
import com.dzungyb.btn_web.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model; // Thêm import này
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.dzungyb.btn_web.entity.Question;
import com.dzungyb.btn_web.service.QuestionService;
import com.dzungyb.btn_web.service.ExamService;



@Controller
@RequestMapping(path = "/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("/HomePageStu")
    public String HomePageStu() {
        
        return "HomePageStu";
    }


    @GetMapping("/taokythi")
    public String taokythi() {
        return "taokythi";
    }
    

    @GetMapping("/list")
    public @ResponseBody List<Exam> showList() {
        List<Exam> examList = examService.getAll();
        String message = "Success";
        if (examList.isEmpty()) {
            message = "Empty";
        }

        return examList;
    }

    @GetMapping("/listDetail")
    public @ResponseBody List<ExamDetailModel> showListDetail() {
        List<ExamDetailModel> examDetailList = examService.getAllDetail();
        String message = "Success";
        if (examDetailList.isEmpty()) {
            message = "Empty";
        }
        return examDetailList;
    }

    @GetMapping(path = "/searchById")
    public @ResponseBody Exam searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Exam result = null;
        try {
            result = examService.searchById(id);
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @GetMapping(path = "/searchDetailById")
    public @ResponseBody ExamDetailModel earchDetailById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ExamDetailModel result = null;
        try {
            ExamDetailModel exam = examService.searchDetailById(id);
            result = exam;
        } catch (Exception ex) {
            result = null;
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping(path = "/create")
    public @ResponseBody ExamDto create(@RequestBody ExamDto examDto) {
        ExamDto result = null;
        try {
            examService.create(examDto);
            result = examDto;
        } catch (Exception | ErrorException ex) {
        }
        return result;
    }

    @PostMapping(path = "/createAll")
    public @ResponseBody ExamQuestionDto createAll(@RequestBody ExamQuestionDto examQuestionDto) {
        ExamQuestionDto result = null;
        try {
            examService.createAll(examQuestionDto);
            result = examQuestionDto;
        } catch (Exception ex) {
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PutMapping(path = "/update")
    public @ResponseBody Exam update(@RequestBody Exam examEntity) {
        Exam result = null;
        try {
            examService.edit(examEntity);
            result = examEntity;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PutMapping(path = "/updateAll")
    public @ResponseBody ExamDetailModel updateAll(@RequestBody ExamDetailModel examDetailModel) {
        ExamDetailModel result = null;
        try {
            examService.editAll(examDetailModel);
            result = examDetailModel;
        } catch (Exception | ErrorException ex) {
        }
        return result;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Integer delete(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Integer result = null;
        try {
            examService.delete(id);
            result = id;
        } catch (Exception | ErrorException ex) {
        }
        return result;
    }
}
