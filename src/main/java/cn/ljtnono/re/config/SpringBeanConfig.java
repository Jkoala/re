package cn.ljtnono.re.config;

import cn.ljtnono.re.ftp.ReFtpClient;
import cn.ljtnono.re.ftp.ReFtpClientPool;
import cn.ljtnono.re.ftp.ReFtpClientPooledObjectFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 配置相关需要注入的类
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
@SpringBootConfiguration
public class SpringBeanConfig {

    /**
     * 配置ftp连接池对象
     * @return ftp连接池
     */
    @Bean
    public ReFtpClientPool reFtpClientObjectPool() {
        return new ReFtpClientPool(new ReFtpClientPooledObjectFactory());
    }

    /**
     * 配置mybatis-plus 分页插件
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
