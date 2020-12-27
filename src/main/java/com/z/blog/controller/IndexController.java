package com.z.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * @author Mr zhang
 * @date 2020/12/15
 */
@RestController
public class IndexController {

    @RequestMapping("/index/{id}")
    public String index(@PathVariable String id) {
//        int i = 9/0;
        System.out.println("-----index 方法-----");
        return "index1";
    }

}
