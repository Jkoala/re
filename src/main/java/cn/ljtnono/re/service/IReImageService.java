package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReImage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
public interface IReImageService extends IService<ReImage> {

    /**
     * 获取博主头像
     * @return 博主头像封装的ReImage类
     */
    ReImage getAvatar();

    /**
     * 获取加我微信的二维码图片
     * @return 加我微信的二维码头像图片
     */
    ReImage getQrCodeWeChat();
}
