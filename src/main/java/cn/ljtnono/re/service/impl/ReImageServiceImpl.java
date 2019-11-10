package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.common.ReEntityRedisCommon;
import cn.ljtnono.re.entity.ReImage;
import cn.ljtnono.re.mapper.ReImageMapper;
import cn.ljtnono.re.service.IReImageService;
import cn.ljtnono.re.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@Service
public class ReImageServiceImpl extends ServiceImpl<ReImageMapper, ReImage> implements IReImageService {

    @Autowired
    private RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(ReImageServiceImpl.class);

    /**
     * 获取博主头像
     *
     * @return 博主头像封装的ReImage类
     */
    @Override
    public ReImage getAvatar() {
        // 尝试从缓存获取
        ReImage avatar = (ReImage) redisUtil.getByPattern("re_image:*:avatar:*");
        if (avatar == null) {
            // 尝试从数据库获取
            ReImage one = getOne(new QueryWrapper<ReImage>().eq("origin_name", "avatar"));
            redisUtil.set(ReEntityRedisCommon.RE_IMAGE_KEY
                    .replace("id", one.getId())
                    .replace("origin_name", one.getOriginName())
                    .replace("type", one.getType())
                    .replace("owner", one.getOwner()), one, RedisUtil.EXPIRE_TIME_DEFAULT);
            return one;
        }
        logger.info("从缓存中加载用户头像数据");
        return avatar;
    }

    /**
     * 获取加我微信的二维码图片
     *
     * @return 加我微信的二维码头像图片
     */
    @Override
    public ReImage getQrCodeWeChat() {
        ReImage qrCodeWeChat = (ReImage) redisUtil.getByPattern("re_image:*:qrcode-wechat:*");
        if (qrCodeWeChat == null) {
            // 尝试从数据库获取
            ReImage one = getOne(new QueryWrapper<ReImage>().eq("origin_name", "qrcode-wechat"));
            redisUtil.set(ReEntityRedisCommon.RE_IMAGE_KEY
                    .replace("id", one.getId())
                    .replace("origin_name", one.getOriginName())
                    .replace("type", one.getType())
                    .replace("owner", one.getOwner()), one, RedisUtil.EXPIRE_TIME_DEFAULT);
            return one;
        }
        logger.info("从缓存中加载用户微信二维码图片");
        return qrCodeWeChat;
    }
}
