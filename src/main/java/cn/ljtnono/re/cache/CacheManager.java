package cn.ljtnono.re.cache;

/**
 * 缓存管理器
 * @author ljt
 * @date 2019/12/23
 * @version 1.0.1
 */
public interface CacheManager <T> {

    void setCache();

    T getCache();
}
