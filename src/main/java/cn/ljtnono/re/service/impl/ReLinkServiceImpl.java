package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReLink;
import cn.ljtnono.re.enumeration.ReEntityRedisKeyEnum;
import cn.ljtnono.re.mapper.ReLinkMapper;
import cn.ljtnono.re.service.IReLinkService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
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
}
