package cn.ljtnono.re.controller;


import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@Controller
@RequestMapping("/blog")
public class ReBlogController extends BaseController {

    @Autowired
    private IReBlogService iReBlogService;

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);

    @GetMapping("/{id}")
    public String getBlogById(@PathVariable String id) {
        ModelMap map = new ModelMap();
        map.addAttribute("key", "fdafd");
        return "forward:/";
    }

    @GetMapping("/")
    public JsonResult getBlogAll() {

        return null;
    }

    @GetMapping("/page/{page}")
    public JsonResult getBlogPageList(@PathVariable String page) {

        return null;
    }

    @PostMapping("/publish")
    public JsonResult publishBlog() {

        return null;
    }

    @PutMapping("/update/{id}")
    public JsonResult updateBlog(@PathVariable Integer id) {

        return null;
    }
}
