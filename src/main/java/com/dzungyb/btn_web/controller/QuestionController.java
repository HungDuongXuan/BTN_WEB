package com.dzungyb.btn_web.controller;

import com.dzungyb.btn_web.entity.Question;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.dto.QuestionDto;
import com.dzungyb.btn_web.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/questions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/list")
    public @ResponseBody List<Question> showList() {
        List<Question> userList = questionService.getAll();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }

        return userList;
    }

    @PostMapping(path = "/create")
    public @ResponseBody QuestionDto create(@Valid @RequestBody QuestionDto questionDto) {
        QuestionDto result = null;
        try {
            questionService.create(questionDto);
            result = questionDto;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PutMapping(path = "/update")
    public Question edit(@Valid @RequestBody Question questionEntity) {
        Question result = null;
        try {
            questionService.edit(questionEntity);
            result = questionEntity;
        } catch (Exception ex) {
            result = null;
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Integer delete(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Integer result = null;
        try {
            questionService.delete(id);
            result = id;
        } catch (Exception | ErrorException ex) {
            result = 0;
        }
        return result;
    }
}
