package cn.ljtnono.re.cache;

public interface CacheManager <T>{

    T getCache(String key);

    boolean removeCache(String key);

    boolean containCache(String key);

    T add(T t);

    T updateCache(String key);

}
