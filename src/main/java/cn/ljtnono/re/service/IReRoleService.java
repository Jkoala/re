package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.RePermission;
import cn.ljtnono.re.entity.ReRole;
import cn.ljtnono.re.service.common.IReEntityService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色服务接口
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
public interface IReRoleService extends IService<ReRole>, IReEntityService<ReRole> {

    /**
     * 根据角色id获取权限列表
     * @param roleId 角色id
     * @return 角色代表的权限id
     */
    List<RePermission> listPermissionByRoleId(@Param("roleId") Integer roleId);
}
