package cn.ljtnono.re.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 处理页面路由的Controller
 * @author ljt
 * @date 2019/10/19
 * @version 1.0
 */
@Controller
public class PageController {

    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String fore(ModelMap map) {
        map.addAttribute("currentPage", "index");
        return "fore/index";
    }

    @GetMapping("/{page}")
    public String foreTemplates(@PathVariable final String page, final ModelMap map) {
        setActivePage(page, map);
        return "fore/" + page;
    }

    @GetMapping({"/admin", "/admin/"})
    public String back(ModelMap map) {
        return "back/index";
    }

    @GetMapping("/admin/{page}")
    public String backTemplates(@PathVariable String page) {
        return "back/" + page;
    }

    /**
     * 根据路由设置当前页面
     * @param map 存储值对象
     */
    private void setActivePage(final String page, final ModelMap map) {
        switch (page) {
            case "articles":
                map.addAttribute("currentPage", "articles");
                break;
            case "support":
                map.addAttribute("currentPage", "support");
                break;
            case "about":
                map.addAttribute("currentPage", "about");
                break;
            default:
                map.addAttribute("currentPage", "index");
        }
    }

}
