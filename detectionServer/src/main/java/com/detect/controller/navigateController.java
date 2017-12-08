package com.detect.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


/**
 * Created by Administrator on 2017/12/07.
 */
@Controller
@EnableAutoConfiguration
public class navigateController {
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String,Object> map){
        map.put("hello","hello");
        return"/hello";
    }
}
