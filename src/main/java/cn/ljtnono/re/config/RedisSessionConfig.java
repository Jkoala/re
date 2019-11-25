package cn.ljtnono.re.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 配置redis-session
 * @author ljt
 * @date 2019/11/10
 * @version 1.0
 */
@SpringBootConfiguration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1900)
public class RedisSessionConfig {
}
