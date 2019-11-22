package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 博客服务接口
 * @author ljt
 * @date 2019/11/16
 * @version 1.0
 */
public interface IReBlogService extends IService<ReBlog>, IReEntityService<ReBlog> {


    /**
     * 获取所有的博客列表
     * @return 返回所有博客列表
     */
    List<ReBlog> listAll();

    /**
     * 新增一条博客记录
     * @param reBlog 需要新增的博客记录
     * @return JsonResult对象，成功返回成功消息，失败返回失败消息
     */
    JsonResult saveBlog(ReBlog reBlog);


    /**
     * 无条件分页查询博客信息
     * @param page 页数
     * @param count 每页条数
     * @return 返回分页数据
     */
    JsonResult listBlogPageReturnJsonResult(Integer page, Integer count);

    /**
     * 根据博客类型分页查询博客列表
     * @param page 当前页
     * @param count 每页查询的条数
     * @param type 博客类型
     * @return 返回根据类型查询的博客分页数据,并且封装在JsonResult中 {@link JsonResult}
     */
    JsonResult listBlogPageByTypeReturnJsonResult(Integer page, Integer count, String type);

    /**
     * 根据博客id获取博客的信息，并封装在JsonResult中
     * @param blogId 博客的id
     * @return 博客详细信息
     */
    JsonResult getByIdReturnJsonResult(Integer blogId);


    /**
     * 获取首页猜你喜欢
     * @return 首页猜你喜欢博客数据
     */
    List<ReBlog> listGuessYouLike();

}
