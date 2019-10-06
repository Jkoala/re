package cn.ljtnono.re;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @author 凌家童
 *  @date 2019/7/7
 *  @version 1.0
 *
*/
@SpringBootApplication
@MapperScan(value = {"cn.ljtnono.root.mapper"})
public class ReApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReApplication.class, args);
    }
}