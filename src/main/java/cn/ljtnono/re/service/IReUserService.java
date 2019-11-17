package cn.ljtnono.re.service;

import cn.ljtnono.re.entity.ReUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户服务类接口
 * @author ljt
 * @date 2019/11/15
 * @version 1.0
 */
public interface IReUserService extends IService<ReUser>, UserDetailsService {

}
