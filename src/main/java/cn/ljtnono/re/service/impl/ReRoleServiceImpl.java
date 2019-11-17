package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.RePermission;
import cn.ljtnono.re.entity.ReRole;
import cn.ljtnono.re.enumeration.GlobalErrorEnum;
import cn.ljtnono.re.exception.GlobalToJsonException;
import cn.ljtnono.re.mapper.ReRoleMapper;
import cn.ljtnono.re.service.IReRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@Service
public class ReRoleServiceImpl extends ServiceImpl<ReRoleMapper, ReRole> implements IReRoleService {

    /**
     * 根据角色id获取权限列表
     *
     * @param roleId 角色id
     * @return 角色代表的权限列表，权限列表为空返回空列表
     */
    @Override
    public List<RePermission> listPermissionByRoleId(Integer roleId) {
        Optional<Integer> id = Optional.ofNullable(roleId);
        // 如果为null 抛出参数缺失异常
        id.orElseThrow(() -> new GlobalToJsonException(GlobalErrorEnum.PARAM_MISSING_ERROR));
        // 如果id > 0 并且 id < 60 执行查询
        Optional<List<RePermission>> rePermissions = id.filter(i -> i > 0 && i < 60)
                .map((i) -> getBaseMapper().listPermissionByRoleId(i));
        return rePermissions.orElseGet(ArrayList::new);
    }
}
