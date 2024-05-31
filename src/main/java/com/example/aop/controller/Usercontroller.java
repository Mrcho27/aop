package com.example.aop.controller;

import com.example.aop.dto.UserDTO;
import com.example.aop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class Usercontroller {
    private final UserService userService;

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @PostMapping("/join")
    public String login(UserDTO userDTO){
        userService.registerUser(userDTO);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password,
                        HttpSession session){
        UserDTO foundUser = userService.findUser(loginId, password);
        session.setAttribute("userId", foundUser.getUserId());
        return "redirect:/board/community";

    }
}
