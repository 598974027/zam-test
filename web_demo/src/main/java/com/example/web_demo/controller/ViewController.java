package com.example.web_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "index");
        return "index";
    }

    @GetMapping("/index2")
    @ResponseBody
    public String index2() {
        return "index2";
    }

    @GetMapping("/index3")
    public Model index3() {
        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        model.addAttribute("message", "index3");
        return model;
    }

    @GetMapping("/index4")
    public View index4() {
        View view = new View() {
            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

            }
        };
        return view;
    }

    @GetMapping("/index5")
    public ModelAndView index5() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index5");
        modelAndView.addObject("message", "index5");
        return modelAndView;
    }

    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //?????????????????????UTF-8??????????????????
        response.setCharacterEncoding("UTF-8");
        //??????????????????UTF-8??????????????????
        response.setContentType("text/html;charset=UTF-8");
        //??????????????????????????????????????????????????????cookie??????
        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("JSESSIONID")) {
                    out.write("session id: " + cookie.getValue() + "\n");
                }
                if (cookie.getName().equals("lastAccessTime")) {
                    Long lastAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    out.write("?????????????????????" + date.toLocaleString() + "\n");
                }
            }
        } else {
            out.write("?????????????????????????????????");
        }
        //???????????????????????????????????????cookie???????????????????????????????????????
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        //??????Cookie???????????????3??????
        cookie.setMaxAge(3 * 60);
        response.addCookie(cookie);
        cookie = new Cookie("JSESSIONID", request.getSession().getId());
        response.addCookie(cookie);
        cookie = new Cookie("abc", "0");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        cookie = new Cookie("def", "-1");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

}
