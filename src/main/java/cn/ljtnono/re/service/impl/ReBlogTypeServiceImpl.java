package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReBlog;
import cn.ljtnono.re.entity.ReBlogType;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReBlogTypeMapper;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogService;
import cn.ljtnono.re.service.IReBlogTypeService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 博客类型服务实现类
 *
 * @author ljt
 * @version 1.0.2
 * @date 2019/12/23
 */
@Service
public class ReBlogTypeServiceImpl extends ServiceImpl<ReBlogTypeMapper, ReBlogType> implements IReBlogTypeService {

    private RedisUtil redisUtil;

    private IReBlogService iReBlogService;

    @Autowired
    public void setReBlogTypeServiceImpl(IReBlogService iReBlogService) {
        this.iReBlogService = iReBlogService;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    private static Logger logger = LoggerFactory.getLogger(ReBlogServiceImpl.class);


    @Override
    public JsonResult listBlogTypeAll() {
        // 首先从缓存中获取，如果缓存中没有的话从数据库获取
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                .replace(":id", ":*")
                .replace(":name", ":*");
        List<ReBlogType> getByPattern = (List<ReBlogType>) redisUtil.getByPattern(redisKey);
        Optional<List<ReBlogType>> optionalGetByPattern = Optional.ofNullable(getByPattern);
        optionalGetByPattern.ifPresent(l -> logger.info("从缓存中获取所有博客类型列表，总条数：" + l.size()));
        List<ReBlogType> reBlogTypeList = optionalGetByPattern.orElseGet(() -> {
            List<ReBlogType> list = list();
            list.forEach(reBlogType -> {
                redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                        .replace(":id", ":" + reBlogType.getId())
                        .replace(":name", ":" + reBlogType.getName()), reBlogType, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            logger.info("从数据库中获取所有博客类型列表，总条数：" + list.size());
            return list;
        });
        return JsonResult.success(reBlogTypeList, reBlogTypeList.size());
    }

    @Override
    public JsonResult listBlogTypePage(Integer page, Integer count) {
        Optional<Integer> optionalPage = Optional.ofNullable(page);
        Optional<Integer> optionalCount = Optional.ofNullable(count);
        optionalPage.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalCount.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalPage.filter(p -> p >= 0 && p <= 1000)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR));
        optionalCount.filter(c -> c >= 0 && c <= 60)
                .orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR));
        // 首先从缓存中获取，如果缓存中没有，那么从数据库中获取
        String redisKey = ReEntityRedisKeyEnum.RE_BLOG_TYPE_PAGE_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count);
        String totalRedisKey = ReEntityRedisKeyEnum.RE_BLOG_TYPE_PAGE_TOTAL_KEY.getKey()
                .replace(":page", ":" + page)
                .replace(":count", ":" + count);
        // 首先从缓存中拿 这里lGet如果查询不到，会自动返回空集合
        List<?> objects = redisUtil.lGet(redisKey, 0, -1);
        if (!objects.isEmpty()) {
            logger.info("从缓存中获取" + page + "页ReBlogType数据，每页获取" + count + "条");
            String getByPattern = (String) redisUtil.getByPattern(totalRedisKey);
            return JsonResult.success((Collection<?>) objects.get(0), ((Collection<?>) objects.get(0)).size()).addField("totalPages", getByPattern.split("_")[0]).addField("totalCount", getByPattern.split("_")[1]);
        } else {
            // 按照时间降序排列
            IPage<ReBlogType> pageResult = page(new Page<>(page, count), new QueryWrapper<ReBlogType>().orderByDesc("modify_time"));
            logger.info("获取" + page + "页ReBlogType数据，每页获取" + count + "条");
            redisUtil.lSet(redisKey, pageResult.getRecords(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            redisUtil.set(totalRedisKey, pageResult.getPages() + "_" + pageResult.getTotal(), RedisUtil.EXPIRE_TIME_PAGE_QUERY);
            return JsonResult.success(pageResult.getRecords(), pageResult.getRecords().size()).addField("totalPages", pageResult.getPages()).addField("totalCount", pageResult.getTotal());
        }
    }

    @Override
    public JsonResult saveEntity(ReBlogType entity) {
        Optional<ReBlogType> optionalReBlogType = Optional.ofNullable(entity);
        optionalReBlogType.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        boolean save = save(entity);
        String key = ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                .replace(":id", ":" + entity.getId())
                .replace(":name", ":" + entity.getName());
        if (save) {
            // 将实体类存储到缓存中去
            redisUtil.set(key, entity, RedisUtil.EXPIRE_TIME_DEFAULT);
            return JsonResult.successForMessage("操作成功！", 200);
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public JsonResult deleteEntityById(Serializable id) {
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer blogTypeId = Integer.parseInt(id.toString());
        if (blogTypeId >= 1) {
            // 在数据库中更新相关标签的状态
            boolean updateResult = update(new UpdateWrapper<ReBlogType>().set("status", 0).eq("id", blogTypeId));
            ReBlogType reBlogType = getById(blogTypeId);
            boolean update = iReBlogService.update(new UpdateWrapper<ReBlog>().set("status", 0).eq("type", reBlogType.getName()));
            if (updateResult && update) {
                // 删除所有相关缓存
                redisUtil.deleteByPattern(ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY
                        .getKey().replace(":id", ":*")
                        .replace(":name", ":*"));
                redisUtil.deleteByPattern(ReEntityRedisKeyEnum.RE_BLOG_TYPE_PAGE_KEY
                        .getKey().replace(":page", ":*")
                        .replace(":count", ":*"));
                redisUtil.deleteByPattern(ReEntityRedisKeyEnum.RE_BLOG_TYPE_PAGE_TOTAL_KEY
                        .getKey().replace(":page", ":*")
                        .replace(":count", ":*"));
                return JsonResult.success(Collections.singletonList(reBlogType), 1);
            } else {
                throw new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR);
            }
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }
    }

    @Override
    public JsonResult updateEntityById(Serializable id, ReBlogType entity) {
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        Optional<ReBlogType> optionalEntity = Optional.ofNullable(entity);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalEntity.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer blogTypeId = Integer.parseInt(id.toString());
        if (blogTypeId >= 1) {
            boolean updateResult = update(new UpdateWrapper<ReBlogType>().setEntity(entity).eq("id", blogTypeId));
            if (updateResult) {
                // 更新操作
                String key = ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                        .replace(":id", ":" + entity.getId())
                        .replace(":name", ":" + entity.getName());
                boolean b = redisUtil.hasKey(key);
                if (b) {
                    redisUtil.set(key, entity, RedisUtil.EXPIRE_TIME_DEFAULT);
                }
                return JsonResult.successForMessage("操作成功", 200);
            } else {
                throw new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR);
            }
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }
    }

    @Override
    public JsonResult getEntityById(Serializable id) {
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer blogTypeId = Integer.parseInt(id.toString());
        if (blogTypeId >= 1) {
            JsonResult jsonResult;
            // 如果缓存中存在，那么首先从缓存中获取
            String key = ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                    .replace(":id", ":" + blogTypeId)
                    .replace(":name", ":*");
            boolean b = redisUtil.hasKeyByPattern(key);
            // 如果存在，那么直接获取
            ReBlogType reBlogType;
            if (b) {
                reBlogType = (ReBlogType) redisUtil.getByPattern(key);
                if (reBlogType == null || reBlogType.getStatus() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                jsonResult = JsonResult.success(Collections.singletonList(reBlogType), 1);
            } else {
                reBlogType = getById(blogTypeId);
                // 如果不存在，那么返回 找不到资源错误
                if (reBlogType == null || reBlogType.getStatus() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                        .replace(":id", ":" + reBlogType.getId())
                        .replace(":name", ":" + reBlogType.getName()), reBlogType, RedisUtil.EXPIRE_TIME_DEFAULT);
                jsonResult = JsonResult.success(Collections.singletonList(reBlogType), 1);
            }
            jsonResult.setMessage("操作成功");
            return jsonResult;
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }
    }

    @Override
    public JsonResult listEntityAll() {
        List<ReBlogType> reBlogTypeList = list();
        Optional<List<ReBlogType>> optionalList = Optional.ofNullable(reBlogTypeList);
        optionalList.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR));
        optionalList.ifPresent((l) -> l.forEach(reBlogType -> {
            redisUtil.set(ReEntityRedisKeyEnum.RE_BLOG_TYPE_KEY.getKey()
                    .replace(":id", ":" + reBlogType.getId())
                    .replace(":name", ":" + reBlogType.getName()), reBlogType, RedisUtil.EXPIRE_TIME_DEFAULT);
        }));
        optionalList.ifPresent(l -> logger.info("从数据库中获取所有博客类型列表，总条数：" + l.size()));
        JsonResult success = JsonResult.success(reBlogTypeList, reBlogTypeList.size());
        success.setMessage("操作成功");
        return success;
    }
}
