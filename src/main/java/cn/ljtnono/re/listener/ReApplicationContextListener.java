package cn.ljtnono.re.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * 容器启动监听器
 * @author ljt
 * @date 2019/10/30
 * @version 1.0
 */
@Component
public class ReApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {


    private static Logger logger = LoggerFactory.getLogger(ReApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 读取web.properties中所有的配置,并且将配置项一一设置进入spring环境中去
        Properties web = new Properties();
        try {
            web.load(ReApplicationContextListener.class.getClassLoader().getResourceAsStream("web.properties"));
            // 将配置文件中的数据放入缓存

        } catch (IOException e) {
            logger.error("");
        }
    }
}
