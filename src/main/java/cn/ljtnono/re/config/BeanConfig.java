package cn.ljtnono.re.config;

import cn.ljtnono.re.ftp.ReFtpClientConfig;
import cn.ljtnono.re.ftp.ReFtpClientObjectPool;
import cn.ljtnono.re.ftp.ReFtpClientPooledObjectFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 配置相关需要注入的类
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
@SpringBootConfiguration
public class BeanConfig {

    /**
     * 配置ftp连接池对象
     * @return ftp连接池
     */
    @Bean
    @Lazy
    public ReFtpClientObjectPool reFtpClientObjectPool() {
        // 默认配置
        return new ReFtpClientObjectPool(new ReFtpClientPooledObjectFactory());
    }
}
