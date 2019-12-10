package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReBlogType;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 博客类型服务接口
 * @author ljt
 * @date 2019/11/16
 * @version 1.0
 */
public interface IReBlogTypeService extends IService<ReBlogType>, IReEntityService<ReBlogType> {

    /**
     * 获取所有的博客类型
     * @return 所有的博客类型
     */
    JsonResult listBlogTypeAll();

    /**
     * 分页获取博客类型
     * @param page 页码
     * @param count 每页显示的条数
     * @return JsonResult 对象
     */
    JsonResult listBlogTypePage(Integer page, Integer count);
}
