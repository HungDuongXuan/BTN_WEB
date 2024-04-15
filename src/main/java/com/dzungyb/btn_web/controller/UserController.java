package com.dzungyb.btn_web.controller;

import com.dzungyb.btn_web.entity.User;
import com.dzungyb.btn_web.entity.UserOut;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.UserModel;
import com.dzungyb.btn_web.model.dto.UserDto;
import com.dzungyb.btn_web.model.dto.UserDtoOut;
import com.dzungyb.btn_web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/DangNhap")
    public String DangNhap() {
        return "DangNhap";
    }

    @PostMapping(path = "/login")
    public @ResponseBody UserOut login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserOut user = userService.login(username, password);
        return user;
    }



    @GetMapping(path = "/list")
    public @ResponseBody List<UserModel> showList() {
        List<UserModel> userList = userService.getAllUser();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        List<UserModel> result = userList;
        return result;
    }

    @GetMapping(path = "/listStudent")
    public @ResponseBody List<UserDtoOut> showListStudent() {
        List<UserDtoOut> userList = userService.getAllStudent();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        List<UserDtoOut> result = userList;
        return result;
    }

    @GetMapping(path = "/searchById")
    public @ResponseBody UserModel searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        UserModel result = null;
        try {
            UserModel user = userService.searchById(id);
            result = user;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @GetMapping(path = "/searchByEmail")
    public @ResponseBody UserModel searchByEmail(@RequestParam(name = "email", required = false, defaultValue = "") String email) {
        UserModel result = null;
        try {
            UserModel user = userService.searchByEmail(email);
            result = user;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PostMapping(path = "/register")
    public @ResponseBody UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = null;
        try {
            userService.register(userDto);
            result = userDto;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PostMapping(path = "/registerAdmin")
    public @ResponseBody UserDto registerAdmin(@Valid @RequestBody UserDto userDto) {
        UserDto result = null;
        try {
            userService.registerAdmin(userDto);
            result = userDto;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PostMapping(path = "/registerStudent")
    public @ResponseBody User registerStudent(@Valid @RequestBody UserDto userDto) {
        User result = null;
        try {
            result = userService.registerStudent(userDto);
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @PutMapping(path = "/update")
    public @ResponseBody User EditUser(@Valid @RequestBody User userEntity) {
        User result = null;
        try {
            userService.edit(userEntity);
            result = userEntity;
        } catch (Exception | ErrorException ex) {
            result = null;
        }
        return result;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Integer deleteUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        Integer result = null;
        try {
            userService.delete(id);
            result = id;
        } catch (Exception ex) {
            result = 0;
        } catch (ErrorException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}