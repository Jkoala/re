package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.common.ReEntityRedisCommon;
import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReBlogMapper;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@Service
public class ReBlogServiceImpl extends ServiceImpl<ReBlogMapper, ReBlog> implements IReBlogService {

    private static Logger logger = LoggerFactory.getLogger(ReBlogServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取首页猜你喜欢
     *
     * @return 首页猜你喜欢博客数据
     */
    @Override
    public List<ReBlog> listGuessYouLike() {
        // 根据view降序获取前6条记录
        // 首先从缓存获取所有数据 TODO 这里琢磨一下怎么提高效率
        List<ReBlog> reBlogList = (List<ReBlog>) redisUtil.getByPattern("re_blog*");
        if (reBlogList == null || reBlogList.size() == 0) {
            List<ReBlog> selectListResult = getBaseMapper().selectList(new QueryWrapper<ReBlog>().last("ORDER BY view LIMIT 6"));
            selectListResult.forEach(reBlog -> {
                redisUtil.set(ReEntityRedisCommon.RE_BLOG_KEY
                        .replace("id", reBlog.getId() + "")
                        .replace("author", reBlog.getAuthor())
                        .replace("title", reBlog.getTitle())
                        .replace("type", reBlog.getType()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            return selectListResult;
        }
        reBlogList.sort(Comparator.comparingInt(ReBlog::getView));
        return reBlogList.subList(0, 5);
    }

    /**
     * 获取所有的博客列表
     *
     * @return 返回所有博客列表
     */
    @Override
    public List<ReBlog> listAll() {
        // 首先从缓存中获取
        List<ReBlog> reBlogList = (List<ReBlog>) redisUtil.getByPattern("re_blog*");
        if (reBlogList == null || reBlogList.size() == 0) {
            // 从数据库中获取
            List<ReBlog> list = getBaseMapper().selectList(null);
            list.forEach(reBlog -> {
                redisUtil.set(ReEntityRedisCommon.RE_BLOG_KEY
                        .replace("id", reBlog.getId() + "")
                        .replace("author", reBlog.getAuthor())
                        .replace("title", reBlog.getTitle())
                        .replace("type", reBlog.getType()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            return list;
        }
        return reBlogList;
    }

    /**
     * 无条件分页查询博客信息
     *
     * @param page  页数
     * @param count 每页条数
     * @return 返回分页数据
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ReBlog> listBlogPage(Integer page, Integer count) {
        if (page == null || count == null) {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR);
        }

        if (page < 0 || count < 0 || page > 1000 || count > 60) {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }

        String redisKey = ReEntityRedisCommon.RE_BLOG_PAGE_KEY
                .replace("page", page + "")
                .replace("count", count + "");
        // 首先从缓存中拿
        List<?> objects = redisUtil.lGet(redisKey, 0, -1);

        if (objects == null || objects.size() == 0) {
            // 从数据库查询
            IPage<ReBlog> pageResult = page(new Page<>(page, count));
            logger.info("获取" + page + "页博客数据，每页获取" + count + "条");
            redisUtil.lSet(redisKey, pageResult.getRecords(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            return pageResult.getRecords();
        }

        logger.info("从缓存中获取" + page + "页博客数据，每页获取" + count + "条");
        return (List<ReBlog>) objects;
    }
}
