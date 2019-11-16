package cn.ljtnono.re.mapper;

import cn.ljtnono.re.entity.RePermission;
import cn.ljtnono.re.entity.ReRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色mapper
 * @author ljt
 * @date 2019/11/15
 * @version 1.0
 *
 */
public interface ReRoleMapper extends BaseMapper<ReRole> {


    /**
     * 根据角色id获取角色的权限列表
     * @param id 角色id
     * @return 角色权限列表
     */
    @Select("SELECT re_permission.* " +
            "FROM re_role_permission " +
            "LEFT JOIN re_role ON re_role_permission.role_id = re_role.id " +
            "LEFT JOIN re_permission ON re_role_permission.permission_id = re_permission.id " +
            "WHERE re_role.id = #{roleId}")
    List<RePermission> listPermissionByRoleId(@Param("roleId") Integer id);

}
