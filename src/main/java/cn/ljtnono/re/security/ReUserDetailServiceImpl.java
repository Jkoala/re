package cn.ljtnono.re.security;

import cn.ljtnono.re.entity.ReRole;
import cn.ljtnono.re.entity.ReUser;
import cn.ljtnono.re.service.IReUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户登录认证授权
 * @author ljt
 * @date 2019/11/18
 * @version 1.0
 */
@Component("reUserDetailService")
public class ReUserDetailServiceImpl implements UserDetailsService {

    private IReUserService iReUserService;

    @Autowired
    public ReUserDetailServiceImpl(IReUserService iReUserService) {
        this.iReUserService = iReUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询出来用户信息和用户权限
        ReUser reUser = iReUserService.getBaseMapper().selectOne(new QueryWrapper<ReUser>().eq("username", username));
        // 如果为null 抛出异常
        Optional.ofNullable(reUser)
                .orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));
        // 根据用户名查询用户的角色
        List<ReRole> reRoleList = iReUserService.listRoleByUserId(reUser.getId());
        Optional.ofNullable(reRoleList)
                .orElseThrow(() -> new RuntimeException("权限异常"));
        List<GrantedAuthority> authorities = new ArrayList<>(10);
        reRoleList.forEach(reRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + reRole.getName()));
        });
        // 根据角色查询出所有的权限
//        List<RePermission> permissionList = new ArrayList<>(8);
//        reRoleList.forEach(reRole -> {
//            List<RePermission> rePermissions = iReRoleService.listPermissionByRoleId(reRole.getId());
//            permissionList.addAll(rePermissions);
//        });
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>(8);
//        permissionList.forEach(rePermission -> {
//            // 将权限表达式添加进去
//            authorityList.add(new SimpleGrantedAuthority(permissionList.get(0).getRes()));
//        });
//        String password = passwordEncoder.encode(reUser.getPassword());
        return new User(username, reUser.getPassword(), authorities);
    }


}
