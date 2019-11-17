package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.RePermission;
import cn.ljtnono.re.entity.ReRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
public interface IReRoleService extends IService<ReRole> {

    /**
     * 根据角色id获取权限列表
     * @param roleId 角色id
     * @return 角色代表的权限id
     */
    List<RePermission> listPermissionByRoleId(@Param("roleId") Integer roleId);
}
