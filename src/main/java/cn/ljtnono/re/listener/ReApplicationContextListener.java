package cn.ljtnono.re.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 容器启动监听器
 * @author ljt
 * @date 2019/10/30
 * @version 1.0
 */
@Component
public class ReApplicationContextListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Logger logger = LoggerFactory.getLogger(ReApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 读取数据库中的配置项，并且将配置项设置到application域中去

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
