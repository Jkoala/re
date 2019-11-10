package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReBlog;
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
    List<ReBlog> listBlogPage(Integer page, Integer count);
}
