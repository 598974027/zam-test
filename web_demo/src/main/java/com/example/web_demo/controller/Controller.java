package com.example.web_demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.web_demo.CountingUtil;
import com.example.web_demo.entity.SysRegionEntity;
import com.example.web_demo.service.MyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/zam")
public class Controller {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/tt", method = RequestMethod.GET)
    public String tt() {
        myService.test();
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(HttpServletResponse response) {
        CountingUtil.getInstance().updateCount("send", 1);
        List<SysRegionEntity> data = myService.test();
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试文档" + ExcelTypeEnum.XLSX.getValue(), "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            EasyExcel.write(out, SysRegionEntity.class).sheet("sheet").doWrite(data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @XxlJob("zamJobHandler")
    public ReturnT<String> execute(String param) {
        System.out.println("XxlJob--XxlJob--XxlJob");
        return ReturnT.SUCCESS;
    }
}
