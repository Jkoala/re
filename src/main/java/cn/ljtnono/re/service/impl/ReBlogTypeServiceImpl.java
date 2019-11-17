package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReBlogType;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.mapper.ReBlogTypeMapper;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReBlogTypeService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 博客类型服务实现类
 *
 * @author ljt
 * @version 1.0
 * @date 2019/11/16
 */
@Service
public class ReBlogTypeServiceImpl extends ServiceImpl<ReBlogTypeMapper, ReBlogType> implements IReBlogTypeService {

    @Autowired
    private RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(ReBlogServiceImpl.class);

    /**
     * 获取所有的博客类型
     *
     * @return 所有的博客类型
     */
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

}
