package cn.ljtnono.re.controller;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.AbstractReController;

import java.io.Serializable;

/**
 * 博客Controller
 * @author ljt
 * @date 2019/11/18
 * @version 1.1
 */
@RestController
@RequestMapping("/blog")
public class ReBlogController extends AbstractReController<ReBlog> {

    @Autowired
    private IReBlogService iReBlogService;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);


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
    public JsonResult updateBlogById(@PathVariable(value = "blogId", required = false) Integer blogId, @RequestParam(value = "blog", required = false) ReBlog reBlog) {
        return JsonResult.success(null, null);
    }


    /**
     * 根据id删除一个博客记录
     * @param blogId 博客id
     * @return {@link JsonResult}
     */
    @DeleteMapping("/{blogId}")
    public JsonResult deleteBlogById(@PathVariable(value = "blogId", required = false) Integer blogId) {

        return null;
    }

    /**
     * 获取实体类的所有列表
     *
     * @return 实体类所有列表
     * 操作成功{request: "success", status: 200, message: "操作成功“, data: {列表}}
     * 操作失败{request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult listEntityAll() {
        return null;
    }

    /**
     * 新增单个实体类
     *
     * @param entity 具体的实体类
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult saveEntity(ReBlog entity) {
        return null;
    }


    /**
     * 根据id更新一个实体类
     *
     * @param id 实体类的id
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult updateEntityById(Serializable id) {
        return null;
    }

    /**
     * 根据id删除一个实体类
     *
     * @param id 实体类id
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“, data: {删除的实体类}}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult deleteEntityById(Serializable id) {
        return null;
    }

    /**
     * 根据id获取一个实体类
     *
     * @param id 实体类id
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“, data: {实体类}}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult getEntityById(Serializable id) {
        return null;
    }
}
