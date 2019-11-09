package cn.ljtnono.re.controller;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.util.RedisUtil;
import cn.ljtnono.re.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@Controller
@RequestMapping("/article")
public class ReBlogController extends BaseController {

    @Autowired
    private IReBlogService iReBlogService;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);

    /**
     * 根据博客的id获取博客内容
     * @param id 博客的id
     * @param modelMap thymeleaf属性集合
     * @return 跳转到article_detail页面
     */
    @GetMapping("/{id}")
    public String getBlogById(@PathVariable final String id, ModelMap modelMap) {
        // 如果参数为空
        if (StringUtil.isEmpty(id)) {
            logger.info("博客id不能为空");
            return "forward:/error/404";
        }

        ReBlog byId = iReBlogService.getById(id);

        if (byId == null) {
            // 如果没有查询到，那么返回404页面
            return "forward:/error/404";
        }
        // TODO 检查博客是否删除， 如果已经删除，返回已经删除的页面
        // 每访问一次，将该博客的浏览量 + 1
        modelMap.addAttribute("blog", byId);
        ReBlog next = iReBlogService.getById(Integer.parseInt(id) + 1);
        iReBlogService.update(new UpdateWrapper<ReBlog>().eq("id", byId.getId()).set("view", byId.getView() + 1));

        if (next != null) {
            modelMap.addAttribute("next", next);
        }

        ReBlog prev = iReBlogService.getById(Integer.parseInt(id) - 1);

        if (next != null) {
            modelMap.addAttribute("prev", prev);
        }

        modelMap.addAttribute("currentPage", "articles");
        return "fore/article";
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
