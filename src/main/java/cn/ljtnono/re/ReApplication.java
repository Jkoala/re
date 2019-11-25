package cn.ljtnono.re;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 启动类
 * @author ljt
 * @date 2019/11/23
 * @version 1.1
 */
@SpringBootApplication
@MapperScan(value = {"cn.ljtnono.re.mapper"})
public class ReApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReApplication.class, args);
    }
}
