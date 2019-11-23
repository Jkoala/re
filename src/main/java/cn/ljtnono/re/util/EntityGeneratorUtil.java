package cn.ljtnono.re.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * 根据数据库表自动生成实体类
 * @author ljt
 * @date 2019/10/27
 * @version 1.0
 */
public class EntityGeneratorUtil {

    /**
     * 根据表字段自动生成实体类
     * @param tableName 生成的实体类对应的表名
     * @param entity 生成的实体类对应的class对象
     * @param path 生成类的路径
     *
     */
    public static void generatorEntity(String tableName, Class<?> entity, String path) {

    }
}
