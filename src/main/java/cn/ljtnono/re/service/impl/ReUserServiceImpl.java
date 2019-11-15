package cn.ljtnono.re.service.impl;

import cn.ljtnono.re.entity.ReUser;
import cn.ljtnono.re.mapper.ReUserMapper;
import cn.ljtnono.re.service.IReUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
public class ReUserServiceImpl extends ServiceImpl<ReUserMapper, ReUser> implements IReUserService, UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询出来用户信息和用户权限
        ReUser reUser = getBaseMapper().selectOne(new QueryWrapper<ReUser>().eq("username", username));
        // 如果为null 抛出异常
        Optional.ofNullable(reUser)
                .orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));

        return null;
    }
}
