package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.RePermission;
import cn.ljtnono.re.entity.ReRole;
import cn.ljtnono.re.entity.ReUser;
import cn.ljtnono.re.mapper.ReRoleMapper;
import cn.ljtnono.re.mapper.ReUserMapper;
import cn.ljtnono.re.service.IReRoleService;
import cn.ljtnono.re.service.IReUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户service类的实现类
 * @author ljt
 * @date 2019/11/15
 * @version 1.0
 *
 */
@Service
public class ReUserServiceImpl extends ServiceImpl<ReUserMapper, ReUser> implements IReUserService {


    @Autowired
    private IReRoleService iReRoleService;
    /**
     * 用户认证和授权实现
     * @param username 用户名
     * @return {@link UserDetails} 返回的是
     * @throws UsernameNotFoundException 找不到用户的情况下抛出该异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询出来用户信息和用户权限
        ReUser reUser = getBaseMapper().selectOne(new QueryWrapper<ReUser>().eq("username", username));
        // 如果为null 抛出异常
        Optional.ofNullable(reUser)
                .orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));
        // 根据用户名查询用户的角色
        List<ReRole> reRoleList = getBaseMapper().listRoleByUserId(reUser.getId());
        Optional.ofNullable(reRoleList)
                .orElseThrow(() -> new RuntimeException("权限异常"));
        // 根据角色查询出所有的权限
        List<RePermission> permissionList = new ArrayList<>(8);
        reRoleList.forEach(reRole -> {
            List<RePermission> rePermissions = iReRoleService.listPermissionByRoleId(reRole.getId());
            permissionList.addAll(rePermissions);
        });
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>(8);
        permissionList.forEach(rePermission -> {
            // 将权限表达式添加进去
            authorityList.add(new SimpleGrantedAuthority(permissionList.get(0).getRes()));
        });

        return new User(username, reUser.getPassword(), authorityList);
    }
}
