package cn.ljtnono.re.cache;

/**
 * Cache 缓存模型
 * @author ljt
 * @date 2019/11/3
 * @version 1.0
 */
public class Cache <T> {

    private String key;

    private T value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
