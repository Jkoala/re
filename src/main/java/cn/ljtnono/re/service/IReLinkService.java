package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReLink;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 链接服务接口
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
public interface IReLinkService extends IService<ReLink>, IReEntityService<ReLink> {

    /**
     * 获取所有外部链接数据
     * @return 所有外部链接数据
     */
    List<ReLink> listOutLinkAll();
}
