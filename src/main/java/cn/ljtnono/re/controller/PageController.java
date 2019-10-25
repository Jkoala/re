package cn.ljtnono.re.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 处理页面跳转的controller
 * @author ljt
 * @date 2019/10/19
 * @version 1.0
 */
@Controller
public class PageController {

    @GetMapping("/re/")
    public String fore(ModelMap map) {
        map.addAttribute("hello", "我套死你猴子！");
        return "fore/index";
    }

    @GetMapping("/admin/")
    public String back(ModelMap map) {
        return "back/index";
    }

    @GetMapping("/admin/{page}")
    public String backTemplates(@PathVariable String page, ModelMap map) {
        return "back/" + page;
    }
}
