package cn.ljtnono.re.controller;

import cn.ljtnono.re.entity.ReBlog;
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
 * @date 2019/11/18
 * @version 1.1
 */
@RestController
@RequestMapping("/blog")
public class ReBlogController extends BaseController {

    @Autowired
    private IReBlogService iReBlogService;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);


    /**
     * 获取全部博客列表
     * @return 带有全部博客列表的JsonResult对象 {@link JsonResult}
     */
    @GetMapping
    public JsonResult listAll() {
        return null;
    }

    /**
     * 根据id获取博客信息
     * @param blogId 博客id
     * @return 带有博客具体信息的JsonResult对象 {@link JsonResult}
     */
    @GetMapping("/{blogId}")
    public JsonResult getBlogById(@PathVariable(value = "blogId", required = false) Integer blogId) {
        return iReBlogService.getByIdReturnJsonResult(blogId);
    }


    @GetMapping("/page/{page}/{count}")
    public JsonResult listPageBlog(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "count", required = false) Integer count) {
        return null;
    }


    /**
     * 新增一条博客记录
     * @param reBlog 新增的博客实体
     * @return {@link JsonResult}
     */
    @PostMapping
    public JsonResult saveBlog(@RequestParam(value = "blog", required = false) ReBlog reBlog) {
        return iReBlogService.saveBlog(reBlog);
    }

    /**
     * 根据id更新博客信息
     * @param blogId 博客的id
     * @return {@link JsonResult}
     */
    @PutMapping("/{blogId}")
    public JsonResult updateBlog(@PathVariable(value = "blogId", required = false) Integer blogId, @RequestParam(value = "blog", required = false) ReBlog reBlog) {
        return JsonResult.success(null, null);
    }


    /**
     * 根据id删除一个博客记录
     * @param blogId 博客id
     * @return {@link JsonResult}
     */
    @DeleteMapping("/{blogId}")
    public JsonResult deleteBlog(@PathVariable(value = "blogId", required = false) Integer blogId) {

        return null;
    }
}
