package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.pojo.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
public interface IReBlogService extends IService<ReBlog> {

    /**
     * 获取首页猜你喜欢
     * @return 首页猜你喜欢博客数据
     */
    List<ReBlog> listGuessYouLike();

    /**
     * 获取所有的博客列表
     * @return 返回所有博客列表
     */
    List<ReBlog> listAll();

    /**
     * 无条件分页查询博客信息
     * @param page 页数
     * @param count 每页条数
     * @return 返回分页数据
     */
    JsonResult listBlogPage(Integer page, Integer count);

    /**
     * 根据博客类型分页查询博客列表
     * @param page 当前页
     * @param count 每页查询的条数
     * @param type 博客类型
     * @return 返回根据类型查询的博客分页数据,并且封装在JsonResult中 {@link JsonResult}
     */
    JsonResult listBlogPageByType(Integer page, Integer count, String type);
}
