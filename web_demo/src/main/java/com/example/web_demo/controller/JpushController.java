package com.example.web_demo.controller;

import cn.jpush.api.push.PushResult;
import com.example.web_demo.jpush.JpushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 功能描述: JpushController
 *
 * @author zhangaomin
 * @time 2021/1/26 14:12
 **/
@RestController
@RequestMapping("/jpush")
public class JpushController {

    @Autowired
    private JpushUtil jpushUtils;

    @GetMapping(value = "/login")
    public String send() {
        String title = "测试标题";
        String content = "得不到的永远在骚动，被偏爱的都有恃无恐！";
        Map<String, String> map = new HashMap();
        map.put("DataType", "1");
        map.put("Ex", "1");
        map.put("DeviceID", "7");
        map.put("Voice", "default");
        PushResult pushResult = null;
        try {
            pushResult = jpushUtils.sendPush(title, content, map, "U2", "U4");
            System.out.println(pushResult.getResponseCode());
//            AliasDeviceListResult aliasDeviceListResult = jpushUtils.getUser("U2");
//            System.out.println(aliasDeviceListResult.registration_ids.size());
            HashSet hashSet = new HashSet();
            hashSet.add("zam");
            hashSet.add("测试");
//            jpushUtils.UpdateDeviceTagAlias("140fe1da9ec6c5fc7af", null, hashSet, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "post  ok";
    }

}