package com.example.aop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/community")
    public String community(){
        return "board/community";
    }

    @GetMapping("/qna")
    public String qna(){
        return "board/qna";
    }
}
