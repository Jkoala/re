package cn.ljtnono.re.controller;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.service.IReBlogTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * rest api控制器 主要是处理暴露出来的接口
 * @author ljt
 * @date 2019/11/9
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IReBlogService iReBlogService;

    @Autowired
    private IReBlogTypeService iReBlogTypeService;

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    /**
     * 此接口只能管理员调用
     * @param request 请求
     * @param response 响应
     * @return 全部博客信息
     */
    @GetMapping("/listBlogAll")
    public JsonResult listBlogAll(HttpServletRequest request, HttpServletResponse response) {
        return iReBlogService.listEntityAll();
    }

    /**
     * 无条件分页获取博客数据
     * @param page 获取的页数
     * @param count 每页获取的条数
     * @return Json 数据串
     */
    @GetMapping("/listBlogPage")
    public JsonResult listBlogPage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "count", required = false) Integer count) {
        return iReBlogService.listBlogPageReturnJsonResult(page, count);
    }

    /**
     * 获取首页猜你喜欢部分博客列表
     * @return 猜你喜欢部分博客列表
     */
    @GetMapping("/getGuessYouLikeBlogList")
    public JsonResult listGuessYouLike() {
        List<ReBlog> reBlogList = iReBlogService.listGuessYouLike();
        return JsonResult.success(reBlogList, reBlogList.size());
    }


    @GetMapping("/listBlogTypeAll")
    public JsonResult listBlogTypeAll() {
        return iReBlogTypeService.listBlogTypeAll();
    }
}
