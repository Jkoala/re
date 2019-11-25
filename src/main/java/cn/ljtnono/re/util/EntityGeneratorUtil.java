package cn.ljtnono.re.util;

/**
 * 根据数据库表自动生成实体类
 * @author ljt
 * @date 2019/10/27
 * @version 1.0
 */
public class EntityGeneratorUtil {

    private EntityGeneratorUtil(){}

    private static EntityGeneratorUtil instance = null;

    /**
     * 单例模式获取实例
     * @return 返回工具类实例
     */
    public static EntityGeneratorUtil getInstance() {
        if (instance == null) {
            synchronized (EntityGeneratorUtil.class) {
                if (instance == null) {
                    instance =  new EntityGeneratorUtil();
                }
            }
        }
        return instance;
    }

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
