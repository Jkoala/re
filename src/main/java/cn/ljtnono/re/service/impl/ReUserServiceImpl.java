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
import org.springframework.security.core.GrantedAuthority;
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

    /**
     * 根据用户ID查询用户角色列表
     * @param userId 用户id
     * @return 用户角色列表
     */
    @Override
    public List<ReRole> listRoleByUserId(Integer userId) {
        return getBaseMapper().listRoleByUserId(userId);
    }
}
