package cn.ljtnono.re;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *  @author 凌家童
 *  @date 2019/7/7
 *  @version 1.0
 *
*/
@SpringBootApplication
@MapperScan(value = {"cn.ljtnono.re.mapper"})
public class ReApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReApplication.class, args);
    }
}
