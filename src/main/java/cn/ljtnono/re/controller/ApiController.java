package cn.ljtnono.re.controller;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * rest api控制器
 * @author ljt
 * @date 2019/11/9
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IReBlogService iReBlogService;

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/getBlogAll")
    public JsonResult listBlogAll(HttpServletRequest request, HttpServletResponse response) {
        // TODO 此接口属于内部接口，只允许管理员调用
        List<ReBlog> list = iReBlogService.list(null);
        return JsonResult.success(list, list.size());
    }

    /**
     * 无条件分页获取博客数据
     * @param page 获取的页数
     * @param count 每页获取的条数
     * @return Json 数据串
     */
    @GetMapping("/getBlog")
    public JsonResult listBlogPage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "count", required = false) Integer count) {
        if (page == null || count == null) {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR);
        }

        if (page < 0 || count < 0 || page > 1000 || count > 60) {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }

        IPage<ReBlog> pageResult = iReBlogService.page(new Page<>(page, count));
        logger.info("获取" + page + "页博客数据，每页获取" + count + "条");
        return JsonResult.success(pageResult.getRecords(), pageResult.getRecords().size());
    }
}
