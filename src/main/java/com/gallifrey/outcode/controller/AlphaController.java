package com.gallifrey.outcode.controller;


import com.gallifrey.outcode.service.AlphaService;
import com.gallifrey.outcode.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    private final AlphaService alphaService;

    @Autowired
    public AlphaController(AlphaService alphaService) {
        this.alphaService = alphaService;
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello Alpha";
    }


    //手动走一次大概底层请求和响应处理的流程
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));
        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>OutCode网<h1>");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // GET请求

    // student?current=1&limit=20 分页请求
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        return "some students\n" + "current" + current + "\nlimit" + limit;
    }

    // student/123 分页请求  符合Restful架构
    @RequestMapping(path = {"/student/{id}", "/student"}, method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable(value = "id", required = false) String id) {
        if (id == null) {
            return "a default student";
        }

        return "a student" + id;
    }

    //Post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //响应HTML数据 Model and View
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 39);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "上海应用技术大学");
        model.addAttribute("age", 50);
        return "demo/view";
    }

    //响应JSON数据 异步请求
    // Java对象 --> JSON字符串 -->JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 25);
        emp.put("salary", 1532);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmpS() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 25);
        emp.put("salary", 1532);

        Map<String, Object> emp2 = new HashMap<>();
        emp2.put("name", "李四");
        emp2.put("age", 65);
        emp2.put("salary", 9800);

        Map<String, Object> emp3 = new HashMap<>();
        emp3.put("name", "王五");
        emp3.put("age", 45);
        emp3.put("salary", 4500);

        list.add(emp);
        list.add(emp2);
        list.add(emp3);
        return list;
    }

    //cookie示例
    @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //  创建cookie
        Cookie cookie=new Cookie("code", CommunityUtil.generateUUID());
        //  设置cookie生效的范围
        cookie.setPath("/community/alpha");
        //  设置cookie的生存时间
        cookie.setMaxAge(60*10);
        //  发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }


    //session示例
    @RequestMapping(path = "session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","Test");
        return "set session";
    }
    @RequestMapping(path = "session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
