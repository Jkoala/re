package cn.ljtnono.re.annotation;

import java.lang.annotation.*;

/**
 * 校验注解
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
public @interface Validate {

}
