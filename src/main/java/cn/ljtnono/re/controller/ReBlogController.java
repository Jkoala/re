package cn.ljtnono.re.controller;

import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.BaseController;

/**
 * 博客Controller
 * @author ljt
 * @date 2019/11/16
 * @version 1.0
 */
@RestController
@RequestMapping("/blog")
public class ReBlogController extends BaseController {

    @Autowired
    private IReBlogService iReBlogService;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);

    @GetMapping("/")
    public JsonResult getAll() {
        return null;
    }


    @GetMapping("/{blogId}")
    public JsonResult getById(@PathVariable("blogId") Integer blogId) {
        return iReBlogService.getByIdReturnJsonResult(blogId);
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
