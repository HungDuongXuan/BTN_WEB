package com.dzungyb.btn_web.controller;

import com.dzungyb.btn_web.entity.Contest;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.ContestDetailModel;
import com.dzungyb.btn_web.model.dto.ContestDto;
import com.dzungyb.btn_web.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contests")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    @GetMapping("/xemkq")
    public String xemketqua() {
        return "xemkq";
    }

    @GetMapping("/list")
    public @ResponseBody List<Contest> showList() {
        List<Contest> userList = contestService.getAll();
        String message = "Success";
        return userList;
    }

    @GetMapping(path = "/searchById")
    public @ResponseBody Contest searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Contest result = null;
        try {
            Contest exam = contestService.searchById(id);
            result = exam;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @GetMapping(path = "/searchDetailById")
    public @ResponseBody ContestDetailModel searchDetailById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ContestDetailModel result = null;
        try {
            ContestDetailModel exam = contestService.searchDetailById(id);
            result = exam;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @GetMapping(path = "/searchDetailByIdUser")
    public @ResponseBody List<ContestDetailModel> searchDetailByIdUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        List<ContestDetailModel> result = null;
        try {
            List<ContestDetailModel> exam = contestService.searchDetailByIdUser(id);
            result = exam;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PostMapping(path = "/create")
    public @ResponseBody ContestDto create(@RequestBody ContestDto contestDto) {
        ContestDto result = null;
        try {
            contestService.create(contestDto);
            result = contestDto;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PutMapping(path = "/update")
    public @ResponseBody Contest update(@RequestBody Contest contestEntity) {
        Contest result = null;
        try {
            contestService.edit(contestEntity);
            result = contestEntity;
        } catch (Exception | ErrorException ex) {
            result = contestEntity;
        }
        return result;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Integer delete(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Integer result = null;
        try {
            contestService.delete(id);
            result = id;
        } catch (Exception ex) {
            result = id;
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
