package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReLink;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReLinkMapper;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReLinkService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 链接服务实现类
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
@Service
public class ReLinkServiceImpl extends ServiceImpl<ReLinkMapper, ReLink> implements IReLinkService {

    @Autowired
    private RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(ReLinkServiceImpl.class);

    /**
     * 获取所有外部链接数据
     *
     * @return 所有外部链接数据
     */
    @Override
    public List<ReLink> listOutLinkAll() {
        // 首先从缓存获取
        List<ReLink> objects = (List<ReLink>) redisUtil.getByPattern("re_link*");
        if (objects == null || objects.size() == 0) {
            // 从数据库获取
            List<ReLink> list = list(new QueryWrapper<ReLink>().eq("type", "外部链接"));
            list.forEach(reLink -> {
                redisUtil.set(ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                        .replace("id", reLink.getId() + "")
                        .replace("name", reLink.getName())
                        .replace("type", reLink.getType()), reLink, RedisUtil.EXPIRE_TIME_DEFAULT);
            });
            return list;
        }
        logger.info("从缓存中加载全部外链数据");
        return objects;
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
    public JsonResult saveEntity(ReLink entity) {
        Optional<ReLink> optionalReBook = Optional.ofNullable(entity);
        optionalReBook.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        boolean save = save(entity);
        String key = ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                .replace(":id", ":" + entity.getId())
                .replace(":name", ":" + entity.getName())
                .replace(":type", ":" + entity.getType());
        if (save) {
            // 将实体类存储到缓存中去
            redisUtil.set(key, entity, RedisUtil.EXPIRE_TIME_DEFAULT);
            return JsonResult.successForMessage("操作成功！", 200);
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR);
        }
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
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer linkId = Integer.parseInt(id.toString());
        if (linkId >= 1) {
            // 在数据库中更新
            boolean updateResult = update(new UpdateWrapper<ReLink>().set("`delete`", 0).eq("id", linkId));
            if (updateResult) {
                // 删除缓存中的相关数据
                ReLink reLink = getById(linkId);
                String key = ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                        .replace(":id", ":" + reLink.getId())
                        .replace(":name", ":" + reLink.getName())
                        .replace(":type", ":" + reLink.getType());
                boolean b = redisUtil.hasKey(key);
                if (b) {
                    redisUtil.del(key);
                }
                return JsonResult.success(Collections.singletonList(reLink), 1);
            } else {
                throw new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR);
            }
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }
    }

    /**
     * 根据id更新一个实体类
     *
     * @param id     实体类的id
     * @param entity 要更新的实体类
     * @return 返回操作结果
     * 操作成功返回（如果有附加信息，那么通过fields字段带回，其中特别注意如果data为null，那么不返回)
     * {request: "success", status: 200, message: "操作成功“}
     * 操作失败返回
     * {request: "fail", status: 具体错误码{@link GlobalErrorEnum}, message: 具体错误信息{@link GlobalErrorEnum}}
     */
    @Override
    public JsonResult updateEntityById(Serializable id, ReLink entity) {
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        Optional<ReLink> optionalEntity = Optional.ofNullable(entity);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalEntity.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer linkId = Integer.parseInt(id.toString());
        if (linkId >= 1) {
            boolean updateResult = update(new UpdateWrapper<ReLink>().setEntity(entity).eq("id", linkId));
            if (updateResult) {
                // 更新操作
                String key = ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                        .replace(":id", ":" + entity.getId())
                        .replace(":name", ":" + entity.getName())
                        .replace(":type", ":" + entity.getType());
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
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer linkId = Integer.parseInt(id.toString());
        if (linkId >= 1) {
            JsonResult jsonResult;
            // 如果缓存中存在，那么首先从缓存中获取
            String key = ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                    .replace(":id", ":" + linkId)
                    .replace(":name", ":*")
                    .replace(":type", ":*");
            boolean b = redisUtil.hasKeyByPattern(key);
            // 如果存在，那么直接获取
            ReLink reLink;
            if (b) {
                reLink = (ReLink) redisUtil.getByPattern(key);
                if (reLink == null || reLink.getStatus() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                jsonResult = JsonResult.success(Collections.singletonList(reLink), 1);
            } else {
                reLink = getById(linkId);
                // 如果不存在，那么返回 找不到资源错误
                if (reLink == null || reLink.getStatus() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                redisUtil.set(ReEntityRedisKeyEnum.RE_LINK_KEY.getKey()
                        .replace(":id", ":" + reLink.getId())
                        .replace(":name", ":" + reLink.getName())
                        .replace(":type", ":" + reLink.getType()), reLink, RedisUtil.EXPIRE_TIME_DEFAULT);
                jsonResult = JsonResult.success(Collections.singletonList(reLink), 1);
            }
            jsonResult.setMessage("操作成功");
            return jsonResult;
        } else {
            throw new GlobalToJsonException(GlobalErrorEnum.PARAM_INVALID_ERROR);
        }
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
        List<ReLink> reLinkList = list();
        Optional<List<ReLink>> optionalList = Optional.ofNullable(reLinkList);
        optionalList.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR));
        optionalList.ifPresent((l) -> l.forEach(reLink -> {
            redisUtil.set(ReEntityRedisKeyEnum.RE_CONFIG_KEY.getKey()
                    .replace(":id", ":" + reLink.getId())
                    .replace(":name", ":" + reLink.getName())
                    .replace(":type", ":" + reLink.getType()), reLink, RedisUtil.EXPIRE_TIME_DEFAULT);
        }));
        optionalList.ifPresent(l -> logger.info("从数据库中获取所有链接列表，总条数：" + l.size()));
        JsonResult success = JsonResult.success(reLinkList, reLinkList.size());
        success.setMessage("操作成功");
        return success;
    }
}
