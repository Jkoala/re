package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
public interface IReLinkService extends IService<ReLink> {

    /**
     * 获取所有外部链接数据
     * @return 所有外部链接数据
     */
    List<ReLink> listOutLinkAll();
}
