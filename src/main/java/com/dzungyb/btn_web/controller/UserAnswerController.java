package com.dzungyb.btn_web.controller;

import com.dzungyb.btn_web.entity.UserAnswer;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.UserAnswerDetailModel;
import com.dzungyb.btn_web.model.dto.UserAnswerDto;
import com.dzungyb.btn_web.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/userAnswers")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    @GetMapping("/list")
    public @ResponseBody List<UserAnswer> showList() {
        List<UserAnswer> userList = userAnswerService.getAll();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        System.out.println(message);

        return userList;
    }

    @GetMapping(path = "/searchById")
    public @ResponseBody UserAnswer searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        UserAnswer result = null;
        try {
            UserAnswer exam = userAnswerService.searchById(id);
            result = exam;
        } catch (Exception ex) {
            result = null;
        }
        return result;
    }

    @GetMapping(path = "/searchDetailByIdContest")
    public @ResponseBody UserAnswerDetailModel searchDetailByIdUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        UserAnswerDetailModel result = null;
        try {
            UserAnswerDetailModel exam = userAnswerService.searchDetailByIdContest(id);
            result = exam;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PostMapping(path = "/start")
    public @ResponseBody UserAnswer start(@RequestBody UserAnswer userAnswerEntity) {
        UserAnswer result = null;
        try {
            userAnswerService.start(userAnswerEntity);
        } catch (Exception | ErrorException ex) {
        }
        return result;
    }

    @PostMapping(path = "/submit")
    public @ResponseBody List<UserAnswer> submit(@RequestBody UserAnswerDto userAnswerDto) {
        List<UserAnswer> result = null;
        try {
            result = userAnswerService.submit(userAnswerDto);
        } catch (Exception ex) {
            result = null;
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    @PutMapping(path = "/update")
//    public UserAnswer edit(@Valid @RequestBody UserAnswer userAnswerEntity) {
//        UserAnswer result = null;
//        try {
//            userAnswerService.edit(userAnswerEntity);
//            result = userAnswerEntity;
//        } catch (Exception ex) {
//            result = null;
//        }
//        return result;
//    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Integer delete(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Integer result = null;
        try {
            userAnswerService.delete(id);
            result = id;
        } catch (Exception | ErrorException ex) {
            result = 0;
        }
        return result;
    }
}