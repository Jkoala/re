package cn.ljtnono.root;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @author 凌家童
 *  @date 2019/7/7
 *  @version 1.0
 *
*/
@SpringBootApplication  // SpringBootApplication 注解告诉springboot这是一个springboot主程序
@MapperScan(value = {"cn.ljtnono.root.mapper"})
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
