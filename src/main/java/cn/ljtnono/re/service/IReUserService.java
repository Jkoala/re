package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReRole;
import cn.ljtnono.re.entity.ReUser;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户服务类接口
 * @author ljt
 * @date 2019/11/15
 * @version 1.0
 */
public interface IReUserService extends IService<ReUser>, IReEntityService<ReUser> {

    /**
     * 根据用户ID查询用户角色列表
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<ReRole> listRoleByUserId(Integer userId);
}
