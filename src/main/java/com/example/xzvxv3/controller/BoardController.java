package com.example.xzvxv3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/posts")
    public String posts() {

        return "posts";
    }
}
