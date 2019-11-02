package cn.ljtnono.re.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置SpringMvc处理静态文件
 * @author ljt
 * @date 2019/10/19
 * @version 1.0
 */
@SpringBootConfiguration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 配置SpringMvc静态资源拦截
     * @param registry 静态资源注册中心
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/static/fore/css/**",
//                "/static/fore/js/**",
//                "/static/fore/images/**",
//                "/static/fore/font/**",
//                "/static/fore/lib/**",
//                "/static/fore/json/**",
//                "/static/back/css/**",
//                "/static/back/js/**",
//                "/static/back/images/**",
//                "/static/back/fonts/**",
//                "/static/back/lib/**")
//                .addResourceLocations("classpath:/static/fore/css/",
//                "classpath:/static/fore/js/",
//                "classpath:/static/fore/images/",
//                "classpath:/static/fore/font/",
//                "classpath:/static/fore/lib/",
//                "classpath:/static/fore/json/",
//                "classpath:/static/back/css/",
//                "classpath:/static/back/js/",
//                "classpath:/static/back/images/",
//                "classpath:/static/back/fonts/",
//                "classpath:/static/back/lib/");
    }
}
