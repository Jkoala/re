package cn.ljtnono.re.controller;


import cn.ljtnono.re.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@RestController
@RequestMapping("/root/re-blog")
public class ReBlogController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);

    @GetMapping("/{id}")
    public JsonResult getBlogById(@PathVariable String id) {

        return null;
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

    @PostMapping("/update")
    public JsonResult updateBlog() {

        return null;
    }
}
