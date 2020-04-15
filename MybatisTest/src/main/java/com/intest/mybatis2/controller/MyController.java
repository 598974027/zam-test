package com.intest.mybatis2.controller;

import com.intest.mybatis2.entity.DbcMd5RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/zam")
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public int test1(HttpServletRequest request) {
        System.out.println("test1");
        return myService.test1();
    }

    /**
     * 接收url里面的参数
     *
     * @param md5
     * @return
     */
    @RequestMapping(value = "/test2/{id}", method = RequestMethod.GET)
    public String test2(@PathVariable("id") String md5) {
        System.out.println("test2");
        return myService.test2(md5).getMd5();
    }

    /**
     * 接收params里面的参数
     *
     * @param md5
     * @return
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3(@RequestParam("id") String md5) {
        System.out.println("test3");
        return myService.test2(md5).getMd5();
    }

    /**
     * 接收body里的raw里面的参数
     * 接收params里面的参数
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(@RequestBody String body, @RequestParam("id") String md5) {
        System.out.println("test4");
        return myService.test2(md5).getMd5();
    }

    /**
     * 接收body里的raw里面的参数,类型json
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public String test5(@RequestBody DbcMd5RecordEntity body) {
        System.out.println("test5");
        return myService.test2(body.getMd5()).getMd5();
    }

    /**
     * 接收body里的raw里面的参数,类型json
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public String test6(@RequestBody Map<String, String> body) {
        System.out.println("test6");
        return myService.test2(body.get("md5").toString()).getMd5();
    }

    /**
     * 接收params里面的参数+body里的form-data里面的参数
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    public int test7(HttpServletRequest request) {
        System.out.println("test7");
        Map<String, String[]> map = request.getParameterMap();
        return myService.test3(map.get("md5")[0]).size();
    }

}
