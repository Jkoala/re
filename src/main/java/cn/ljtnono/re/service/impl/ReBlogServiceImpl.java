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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 博客服务实现类
 * @author ljt
 * @date 2019/11/16
 * @version 1.0
 */
@Service
public class ReBlogServiceImpl extends ServiceImpl<ReBlogMapper, ReBlog> implements IReBlogService {

    private static Logger logger = LoggerFactory.getLogger(ReBlogServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取所有的博客列表
     *
     * @return 返回所有博客列表
     */
    @Override
    public List<ReBlog> listAll() {
        // 直接从数据库中获取所有 这里mybatis-plus 会返回空集合 TODO 这里改成先从缓存中获取
        List<ReBlog> list = getBaseMapper().selectList(null);
        // 将数据写入缓存中
        Optional<List<ReBlog>> optionalList = Optional.ofNullable(list);
        optionalList.ifPresent(l -> l.forEach(reBlog -> redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_KEY.getKey()
                .replace("id", reBlog.getId() + "")
                .replace("author", reBlog.getAuthor())
                .replace("title", reBlog.getTitle())
                .replace("type", reBlog.getType()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT)));
        optionalList.ifPresent(l -> logger.info("从数据库中获取所有博客列表，总条数：" + l.size()));
        return list;
    }

    /**
     * 新增一条博客记录
     *
     * @param reBlog 需要新增的博客记录
     * @return JsonResult对象，成功返回成功消息，失败返回失败消息
     */
    @Override
    public JsonResult saveBlog(ReBlog reBlog) {
        return null;
    }
    /**
     * 获取首页猜你喜欢
     *
     * @return 首页猜你喜欢博客数据
     */
    @Override
    public List<ReBlog> listGuessYouLike() {
        // 根据view降序获取前6条记录
        // 首先从缓存获取所有数据 TODO 这里琢磨一下怎么提高效率
        List<ReBlog> byPattern = (List<ReBlog>) redisUtil.getByPattern("re_blog:*:*:*:*");
        return Optional.ofNullable(byPattern).map(reBlogList -> {
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
     * 无条件分页查询博客信息
     *
     * @param page  页数
     * @param count 每页条数
     * @return 返回分页数据
     */
    @Override
    public JsonResult listBlogPageReturnJsonResult(Integer page, Integer count) {
        Optional<Integer> optionalPage = Optional.ofNullable(page);
        Optional<Integer> optionalCount = Optional.ofNullable(count);
        optionalPage.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalCount.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalPage.filter(p -> p >= 0 && p <= 1000)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR));
        optionalCount.filter(c -> c >= 0 && c <= 60)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR));
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_PAGE_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count);
        String totalRedisKey = ReEntityRedisKeyEnum.RE_BLOG_PAGE_TOTAL_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count);
        // 首先从缓存中拿 这里lGet如果查询不到，会自动返回空集合
        List<?> objects = redisUtil.lGet(redisKey, 0, -1);
        if (!objects.isEmpty()) {
            logger.info("从缓存中获取" + page + "页博客数据，每页获取" + count + "条");
            String getByPattern = (String) redisUtil.getByPattern(totalRedisKey);
            return JsonResult.success((Collection<?>) objects.get(0), ((Collection<?>)objects.get(0)).size()).addField("totalPages", getByPattern.split("_")[0]).addField("totalCount", getByPattern.split("_")[1]);
        } else {
            IPage<ReBlog> pageResult = page(new Page<>(page, count));
            logger.info("获取" + page + "页博客数据，每页获取" + count + "条");
            redisUtil.lSet(redisKey, pageResult.getRecords(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            redisUtil.set(totalRedisKey, pageResult.getPages() + "_" + pageResult.getTotal(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            return JsonResult.success(pageResult.getRecords(), pageResult.getRecords().size()).addField("totalPages", pageResult.getPages()).addField("totalCount", pageResult.getTotal());
        }
    }

    /**
     * 根据博客类型分页查询博客列表
     * TODO 还未完成
     * @param page  当前页
     * @param count 每页查询的条数
     * @param type  博客类型
     * @return 返回根据类型查询的博客分页数据, 并且封装在JsonResult中 {@link JsonResult}
     */
    @Override
    public JsonResult listBlogPageByTypeReturnJsonResult(Integer page, Integer count, String type) {
        // 首先从缓存中获取
        Optional.ofNullable(page);
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_PAGE_TYPE_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count)
                .replace(":type", type);
        return null;
    }

    /**
     * 根据博客id获取博客的信息，并封装在JsonResult中
     *
     * @param blogId 博客的id
     * @return 博客详细信息
     */
    @Override
    public JsonResult getByIdReturnJsonResult(Integer blogId) {
        // 首先从缓存获取，如果缓存不存在，那么从数据库获取
        Optional<Integer> optionalBlogId = Optional.ofNullable(blogId);
        optionalBlogId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalBlogId.filter(id -> id >= 10001)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR));
        ReBlog byPattern = (ReBlog) redisUtil
                .getByPattern(ReEntityRedisKeyEnum.RE_BLOG_KEY
                .getKey().replace(":id", ":" + blogId)
                .replace(":author", ":*")
                .replace(":title", ":*")
                .replace(":type", ":*"));
        Optional<ReBlog> optionalByPattern = Optional.ofNullable(byPattern);
        optionalByPattern.ifPresent(reBlog -> logger.info("从缓存中获取博客数据: id = " + blogId));
        ReBlog reBlogByOptional = optionalByPattern.orElseGet(() -> {
            ReBlog reBlog = getBaseMapper().selectById(blogId);
            if (reBlog == null) {
                throw new GlobalToJsonException(GlobalErrorEnum.NOTFOUND_ERROR);
            }
            redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_KEY.getKey()
                    .replace(":id", ":" + reBlog.getId())
                    .replace(":author", ":" + reBlog.getAuthor()), reBlog, RedisUtil.EXPIRE_TIME_DEFAULT);
            logger.info("从数据库中获取博客数据: id = " + blogId);
            return reBlog;
        });
        return JsonResult.success(Collections.singletonList(reBlogByOptional), 1);
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
        Optional<ReBlog> optionalReBlog = Optional.ofNullable(entity);
        optionalReBlog.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        save(entity);
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
}
