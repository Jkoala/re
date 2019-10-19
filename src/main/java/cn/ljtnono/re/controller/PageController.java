package cn.ljtnono.re.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 处理页面跳转的controller
 * @author ljt
 * @date 2019/10/19
 * @version 1.0
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String index() {

        return "fore/index";
    }
}
