package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReSkill;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReSkillMapper;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReSkillService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 技能类服务实现类
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
@Service
public class ReSkillServiceImpl extends ServiceImpl<ReSkillMapper, ReSkill> implements IReSkillService {

    @Autowired
    private RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(ReSkillServiceImpl.class);


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
    public JsonResult saveEntity(ReSkill entity) {
        Optional<ReSkill> reSkill = Optional.ofNullable(entity);
        reSkill.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        boolean save = save(entity);
        String key = ReEntityRedisKeyEnum.RE_SKILL_KEY.getKey()
                .replace(":id", ":" + entity.getId())
                .replace(":name", ":" + entity.getName())
                .replace(":owner", ":" + entity.getOwner());
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
        Integer skillId = Integer.parseInt(id.toString());
        if (skillId >= 1) {
            // 在数据库中更新
            boolean updateResult = update(new UpdateWrapper<ReSkill>().set("`delete`", 0).eq("id", skillId));
            if (updateResult) {
                // 删除缓存中的相关数据
                ReSkill reSkill = getById(skillId);
                String key = ReEntityRedisKeyEnum.RE_SKILL_KEY.getKey()
                        .replace(":id", ":" + reSkill.getId())
                        .replace(":name", ":" + reSkill.getName())
                        .replace(":owner", ":" + reSkill.getOwner());
                boolean b = redisUtil.hasKey(key);
                if (b) {
                    redisUtil.del(key);
                }
                return JsonResult.success(Collections.singletonList(reSkill), 1);
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
    public JsonResult updateEntityById(Serializable id, ReSkill entity) {
        Optional<Serializable> optionalId = Optional.ofNullable(id);
        Optional<ReSkill> optionalEntity = Optional.ofNullable(entity);
        optionalId.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        optionalEntity.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        Integer skillId = Integer.parseInt(id.toString());
        if (skillId >= 1) {
            boolean updateResult = update(new UpdateWrapper<ReSkill>().setEntity(entity).eq("id", skillId));
            if (updateResult) {
                // 更新操作
                String key = ReEntityRedisKeyEnum.RE_SKILL_KEY.getKey()
                        .replace(":id", ":" + entity.getId())
                        .replace(":name", ":" + entity.getName())
                        .replace(":owner", ":" + entity.getOwner());
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
        Integer skillId = Integer.parseInt(id.toString());
        if (skillId >= 1) {
            JsonResult jsonResult;
            // 如果缓存中存在，那么首先从缓存中获取
            String key = ReEntityRedisKeyEnum.RE_SKILL_KEY.getKey()
                    .replace(":id", ":" + skillId)
                    .replace(":name", ":*")
                    .replace(":owner", ":*");
            boolean b = redisUtil.hasKeyByPattern(key);
            // 如果存在，那么直接获取
            ReSkill reSkill;
            if (b) {
                reSkill = (ReSkill) redisUtil.getByPattern(key);
                if (reSkill == null || reSkill.getDelete() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                jsonResult = JsonResult.success(Collections.singletonList(reSkill), 1);
            } else {
                reSkill = getById(skillId);
                // 如果不存在，那么返回 找不到资源错误
                if (reSkill == null || reSkill.getDelete() == 0) {
                    throw new GlobalToJsonException(GlobalErrorEnum.NOT_EXIST_ERROR);
                }
                redisUtil.set(ReEntityRedisKeyEnum.RE_SKILL_KEY.getKey()
                        .replace(":id", ":" + reSkill.getId())
                        .replace(":name", ":" + reSkill.getName())
                        .replace(":owner", ":" + reSkill.getOwner()), reSkill, RedisUtil.EXPIRE_TIME_DEFAULT);
                jsonResult = JsonResult.success(Collections.singletonList(reSkill), 1);
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
        List<ReSkill> reSkillList = list();
        Optional<List<ReSkill>> optionalList = Optional.ofNullable(reSkillList);
        optionalList.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.SYSTEM_ERROR));
        optionalList.ifPresent((l) -> l.forEach(reSkill -> {
            redisUtil.set(ReEntityRedisKeyEnum.RE_CONFIG_KEY.getKey()
                    .replace(":id", ":" + reSkill.getId())
                    .replace(":name", ":" + reSkill.getName())
                    .replace(":owner", ":" + reSkill.getOwner()), reSkill, RedisUtil.EXPIRE_TIME_DEFAULT);
        }));
        optionalList.ifPresent(l -> logger.info("从数据库中获取所有技能列表，总条数：" + l.size()));
        JsonResult success = JsonResult.success(reSkillList, reSkillList.size());
        success.setMessage("操作成功");
        return success;
    }
}
