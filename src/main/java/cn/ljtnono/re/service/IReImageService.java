package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReImage;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 图像类接口
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
public interface IReImageService extends IService<ReImage>, IReEntityService<ReImage> {

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

    /**
     * 获取微信扫码支付的二维码图片
     * @return 扫码支付二维码
     */
    ReImage getQrCodeWeChatSk();

    /**
     * 获取支付宝扫码支付图片
     * @return 支付宝扫码支付的二维码
     */
    ReImage getQrCodeZfb();
}
