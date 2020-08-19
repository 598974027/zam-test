package com.example.web_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@RequestMapping("/test3")
public class MyController3 {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "this is index jsp page");
        return "index";
    }

    @GetMapping("/index2")
    @ResponseBody
    public String index2() {
        return "index";
    }

    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收
        response.setContentType("text/html;charset=UTF-8");
        //获取浏览器访问访问服务器时传递过来的cookie数组
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
                    out.write("上次访问时间：" + date.toLocaleString() + "\n");
                }
            }
        } else {
            out.write("这是您第一次访问本站！");
        }
        //设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        //设置Cookie的有效期为3分钟
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
