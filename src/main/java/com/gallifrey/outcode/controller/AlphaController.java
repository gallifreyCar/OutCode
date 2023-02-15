package com.gallifrey.outcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/Alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Alpha";
    }
}
