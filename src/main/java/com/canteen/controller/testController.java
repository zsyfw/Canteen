package com.canteen.controller;


import com.alibaba.fastjson.JSONObject;;
import org.springframework.web.bind.annotation.*;

import static org.apache.coyote.http11.Constants.a;

@RestController         //如果要求方法返回的是json格式数据，而不是跳转页面，可以直接在类上标注@RestController
@RequestMapping("/test")
public class testController {



    @GetMapping("/hello")
    public String hello(){
        return "helloworld";
    }

    @GetMapping("/upload")
    public int sum(@RequestParam("num1") int a,@RequestParam("num2") int b){
        return a+b;
    }
}
