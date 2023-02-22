package com.gallifrey.outcode.controller;

import com.gallifrey.outcode.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/Alpha")
public class AlphaController {

    private final AlphaService alphaService;
    @Autowired
    public AlphaController(AlphaService alphaService){
        this.alphaService=alphaService;
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }



    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Alpha";
    }

}
