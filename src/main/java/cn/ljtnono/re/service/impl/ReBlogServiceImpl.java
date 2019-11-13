package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReBlogMapper;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
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
        return Optional.ofNullable((List<ReBlog>) redisUtil.getByPattern("re_blog:*:*:*:*")).map(reBlogList -> {
            if (reBlogList.size() >= 6) {
                return reBlogList.subList(0, 6);
            }
            return reBlogList;
        }).orElseGet(() -> {
            List<ReBlog> selectListResult = getBaseMapper().selectList(new QueryWrapper<ReBlog>().last("ORDER BY view LIMIT 6"));
            selectListResult.forEach(reBlog -> {
                redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_KEY.getKey()
                        .replace("id", reBlog.getId() + "")
                        .replace("author", reBlog.getAuthor())
                        .replace("title", reBlog.getTitle())
                        .replace("type", reBlog.getType()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            return selectListResult;
        });
    }

    /**
     * 获取所有的博客列表
     *
     * @return 返回所有博客列表
     */
    @Override
    public List<ReBlog> listAll() {
        // 首先从缓存中获取
        return Optional.ofNullable((List<ReBlog>) redisUtil.getByPattern("re_blog:*:*:*:*")).orElseGet(() -> {
            // 从数据库中获取
            List<ReBlog> list = getBaseMapper().selectList(null);
            list.forEach(reBlog -> {
                redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_KEY.getKey()
                        .replace("id", reBlog.getId() + "")
                        .replace("author", reBlog.getAuthor())
                        .replace("title", reBlog.getTitle())
                        .replace("type", reBlog.getType()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            return list;
        });
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
        Optional.ofNullable(page)
                .filter(p -> p >= 0 && p <= 1000)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Optional.ofNullable(count)
                .filter(c -> c >= 0 && c <= 60)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_PAGE_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count);
        // 首先从缓存中拿 TODO 解决这里第一次也能拿到的问题
        List<?> objects = Optional.ofNullable(redisUtil.lGet(redisKey, 0, -1)).map(o -> {
            logger.info("从缓存中获取" + page + "页博客数据，每页获取" + count + "条");
            return o;
        }).orElseGet(() -> {
            IPage<ReBlog> pageResult = page(new Page<>(page, count));
            logger.info("获取" + page + "页博客数据，每页获取" + count + "条");
            redisUtil.lSet(redisKey, pageResult.getRecords(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            List<ReBlog> records = pageResult.getRecords();
            return Arrays.asList(records.toArray());
        });
        return (List<ReBlog>) objects;
    }

    /**
     * 根据博客类型分页查询博客列表
     *
     * @param page  当前页
     * @param count 每页查询的条数
     * @param type  博客类型
     * @return 返回根据类型查询的博客分页数据, 并且封装在JsonResult中 {@link JsonResult}
     */
    @Override
    public JsonResult listBlogPageByType(Integer page, Integer count, String type) {
        // 首先从缓存中获取
        Optional.ofNullable(page);
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_PAGE_TYPE_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count)
                .replace(":type", type);
        return null;
    }
}
