package cn.ljtnono.re.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ljt
 * @version 1.1.0
 * 基于spring和redis的redisTemplate工具类
 * 针对所有的hash 都是以h开头的方法
 * 针对所有的Set 都是以s开头的方法  不含通用方法
 * 针对所有的List 都是以l开头的方法
 * 为避免不必要的错误，key值都设置了不能为空串
 * @date 2019/12/19
 */
@Component
public class RedisUtil {

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 这里使用构造器注入
     *
     * @param redisTemplate redis操作工具
     */
    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 默认缓存时间30d
     */
    public static Integer EXPIRE_TIME_DEFAULT = 24 * 60 * 60 * 30;

    /**
     * 分页查询缓存时间为2h
     */
    public static Integer EXPIRE_TIME_PAGE_QUERY = 60 * 60 * 2;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return true 成功 false 失败
     */
    public boolean expire(final String key, final long time) {
        try {
            if (time > 0 && key != null && !key.isEmpty()) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("指定" + key + "失效时间失败, 原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 根据正则表达式删除key
     *
     * @param pattern 正则表达式
     */
    public void deleteByPattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            Long delete = redisTemplate.delete(keys);
            logger.info("已删除满足正则表达式[" + pattern + "]的key，删除数量：" + delete);
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0 代表为永久有效 返回-2表示key不存在 返回-1表示key没有设置过期策略
     * @throws NullPointerException key为null时抛出
     */
    public long getExpire(final String key) {
        if (null == key) {
            throw new NullPointerException("key不能为null");
        }
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 正则表达式匹配是否存在该key
     *
     * @param pattern 正则表达式
     * @return true 存在 false 不存在
     */
    public boolean hasKeyByPattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        return keys != null && !keys.isEmpty();
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     * @throws NullPointerException key值为null时抛出
     */
    public boolean hasKey(final String key) {
        if (null == key) {
            throw new NullPointerException("key值不能为null");
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public void del(final String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
                logger.info("删除键[" + keys[0] + "]");
            } else {
                logger.info("删除键" + Arrays.toString(keys));
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }


    //============================String相关=============================//

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值 如果 key为null 返回 null
     */
    public Object get(final String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     *
     * @param pattern 键,带有正则表达式
     * @return 值 如果值不存在返回 null 如果值有一个返回一个对象， 如果值有多个，返回List<Object> 类型
     */
    public Object getByPattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys == null || keys.size() == 0) {
            return null;
        }
        List<Object> objects = redisTemplate.opsForValue().multiGet(keys);
        if (objects == null || objects.size() == 0) {
            return null;
        } else if (objects.size() == 1) {
            return objects.get(0);
        } else {
            return objects;
        }
    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(final String key, final Object value) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            if (value == null) {
                throw new IllegalArgumentException("value不能为null");
            }
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("设置缓存[" + key + "]失败, 原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(final String key, final Object value, final long time) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            if (value == null) {
                throw new IllegalArgumentException("value不能为null");
            }
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("设置缓存[" + key + "]失败, 原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 值递增
     *
     * @param key 键
     * @param delta 每次递增的值
     * @return 递增后的值
     */
    public long incr(final String key, final long delta) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key值不能为" + key);
        }
        if (delta < 0) {
            throw new IllegalArgumentException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 值递减
     *
     * @param key 键
     * @param delta 每次递减的值
     * @return 递减后的值
     */
    public long decr(final String key, final long delta) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key值不能为" + key);
        }
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //============================Map相关=============================//

    /**
     * 获取hash中的某一项
     *
     * @param key  键 不能为null和空串
     * @param item 项 不能为null
     * @throws IllegalArgumentException 当key或者item为null或者空字符串时抛出
     * @return 值
     */
    public Object hGet(final String key, final String item) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key值不能为" + key);
        }
        if (item == null || item.isEmpty()) {
            throw new IllegalArgumentException("item值不能为" + key);
        }
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash中的所有键值
     *
     * @param key 键
     * @throws IllegalArgumentException key值为null或者空字符串时抛出
     * @return 对应的多个键值
     */
    public Map<Object, Object> hGetAll(final String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key值不能为" + key);
        }
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置多个hash值
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hMultiSet(final String key, final Map<String, Object> map) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            if (map == null) {
                throw new IllegalArgumentException("map不能为null");
            }
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            logger.error("设置多个hash值失败，原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 设置多个hash值并设置过期时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hMultiSet(final String key, final Map<String, Object> map, final long time) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            if (map == null) {
                throw new IllegalArgumentException("map不能为null");
            }
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("设置多个hash值失败，原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hSet(final String key, final String item, final Object value) {
        try {
            if (key == null || key.isEmpty()) {
                if (item == null || item.isEmpty()) {
                    throw new IllegalArgumentException("item值不能为" + key);
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            logger.error("设置多个hash值失败，原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hSet(final String key, final String item, final Object value, final long time) {
        try {
            if (key == null || key.isEmpty()) {
                if (item == null || item.isEmpty()) {
                    throw new IllegalArgumentException("item值不能为" + key);
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("设置多个hash值失败，原因：" + e.getMessage());
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hDel(final String key, final Object... item) {
        if (key == null || key.isEmpty()) {
            if (item == null) {
                throw new IllegalArgumentException("item值不能为null");
            }
            throw new IllegalArgumentException("key值不能为" + key);
        }
        if (item.length > 0) {
            redisTemplate.opsForHash().delete(key, item);
        }
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @throws IllegalArgumentException 当key或者item为null或者空串的时候抛出该异常
     * @return true 存在 false不存在
     */
    public boolean hHasKey(final String key, final String item) {
        if (key == null || key.isEmpty()) {
            if (item == null) {
                throw new IllegalArgumentException("item值不能为null");
            }
            throw new IllegalArgumentException("key值不能为" + key);
        }
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @throws IllegalArgumentException 当key或者item为null或者空串时抛出此异常
     * @param by   要增加几(大于0)
     * @return 增加后的值
     */
    public double hIncr(final String key, final String item, final double by) {
        if (key == null || key.isEmpty()) {
            if (item == null) {
                throw new IllegalArgumentException("item值不能为null");
            }
            throw new IllegalArgumentException("key值不能为" + key);
        }
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @throws IllegalArgumentException 当key或者item为null或者空串时抛出此异常
     * @param by   要减少记(小于0)
     * @return 减少后的值
     */
    public double hDecr(final String key, final String item, final double by) {
        if (key == null || key.isEmpty()) {
            if (item == null) {
                throw new IllegalArgumentException("item值不能为null");
            }
            throw new IllegalArgumentException("key值不能为" + key);
        }
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set相关=============================//

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return 如果该键不存在，那么返回null,如果存在，那么返回Set
     */
    public Set<Object> sGet(final String key) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            logger.error("获取set中的所有值失败，原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(final String key, final Object value) {
        try {
            if (key == null || key.isEmpty()) {
                if (value == null) {
                    throw new IllegalArgumentException("value值不能为null");
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            logger.error("查询value失败，原因" + e.getMessage());
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数， 失败返回0
     */
    public long sSet(final String key, final Object... values) {
        try {
            if (key == null || key.isEmpty()) {
                if (values == null) {
                    throw new IllegalArgumentException("values值不能为null");
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            logger.error("设置多个set类型值失败，原因" + e.getMessage());
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数 失败返回0
     */
    public long sSetAndTime(final String key, final long time, final Object... values) {
        try {
            if (key == null || key.isEmpty()) {
                if (values == null) {
                    throw new IllegalArgumentException("values值不能为null");
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            logger.error("设置多个set类型值失败，原因" + e.getMessage());
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return set的长度，如果不存在返回0
     */
    public long sGetSetSize(final String key) {
        try {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key值不能为" + key);
            }
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            logger.error("获取set类型长度失败，原因：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数，不存在返回0
     */
    public long setRemove(final String key, final Object... values) {
        try {
            if (key == null || key.isEmpty()) {
                if (values == null) {
                    throw new IllegalArgumentException("values值不能为null");
                }
                throw new IllegalArgumentException("key值不能为" + key);
            }
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            logger.error("移除多个set值失败，原因：" + e.getMessage());
            return 0;
        }
    }

    //===============================list相关=================================//

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(final String key, final long start, final long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(final String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
