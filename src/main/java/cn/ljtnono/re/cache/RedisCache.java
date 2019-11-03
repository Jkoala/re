package cn.ljtnono.re.cache;

/**
 * redis缓存模型
 * @author ljt
 * @date 2019/11/3
 * @version 1.0
 */
public class RedisCache extends Cache {

    private RedisCacheType type;

    private int expires;

    /**
     * Redis缓存类型，String， list， hash 等等
     */
    public enum RedisCacheType {

    }

    public RedisCacheType getType() {
        return type;
    }

    public void setType(RedisCacheType type) {
        this.type = type;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
