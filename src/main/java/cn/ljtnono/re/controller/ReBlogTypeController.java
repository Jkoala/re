package cn.ljtnono.re.controller;


import cn.ljtnono.re.entity.ReBlogType;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.AbstractReController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 博客类型Controller
 * @author ljt
 * @date 2019/11/18
 * @version 1.0
 */
@RestController
@RequestMapping("/blog_type")
public class ReBlogTypeController extends AbstractReController<ReBlogType> {

    @Autowired
    private IReBlogTypeService iReBlogTypeService;

    /**
     * 获取实体类的所有列表
     *
     * @return 实体类所有列表
     * 操作成功{request: "success", status: 200, message: "操作成功“, data: {列表}}
     * 操作失败{request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    @GetMapping
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
    @PostMapping
    public JsonResult saveEntity(ReBlogType entity) {
        ReBlogType reBlogType;
        System.out.println(entity.toString());
        reBlogType = (ReBlogType) entity;
        System.out.println(entity.toString());
        return null;
    }

    /**
     * 根据id更新一个实体类
     *
     * @param id 实体类的id
     * @param entity 需要更新的实体类
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult updateEntityById(Serializable id, ReBlogType entity) {
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


    /**
     * 分页查询，如果分页查询带有type参数，那么是按照类型的分页查询
     * @param page 页码
     * @param count 每页显示的条数
     * @return JsonResult 格式对象
     */
    @GetMapping("/listBlogTypePage")
    public JsonResult listBlogTypePage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "count", required = false) Integer count, HttpServletRequest request) {
        // 判断是否有type字段
        StringBuffer url = request.getRequestURL();
        // 处理不同根据type字段来查询的相关业务
        if (url.toString().contains("type")) {

        }
        return iReBlogTypeService.listBlogTypePage(page, count);
    }

}
