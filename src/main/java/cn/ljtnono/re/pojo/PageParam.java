package cn.ljtnono.re.pojo;

import cn.ljtnono.re.util.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *  封装的分页查询的条件
 *  @author ljt
 *  @date 2019/10/13
 *  @version 1.1
*/
public class PageParam implements Serializable{

    /** 持久化UID */
    private static final long serialVersionUID = 6823433291117800742L;

    /** 页码 */
    private Integer page;

    /** 每页条数 */
    private Integer limit;

    /** 新增的字段 */
    private Map<String, Object> fields;

    /**
     * 为此PageParam对象添加新的字段
     * @param key 添加的字段名
     * @param value 添加的字段值
     * @return 当前PageParam对象
     */
    public PageParam addField(String key, Object value) {
        if (!StringUtil.isEmpty(key)) {
            if (this.fields == null) {
                this.fields = new HashMap<>(5);
            }
            this.fields.put(key, value);
        }
        return this;
    }

    /**
     * 无参构造方便new对象
     */
    public PageParam() {}

    private PageParam(Builder builder) {
        setPage(builder.page);
        setLimit(builder.limit);
        setFields(builder.fields);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PageParam copy) {
        Builder builder = new Builder();
        builder.page = copy.getPage();
        builder.limit = copy.getLimit();
        builder.fields = copy.getFields();
        return builder;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }


    public static final class Builder {
        private Integer page;
        private Integer limit;
        private Map<String, Object> fields;

        private Builder() {
        }

        public Builder page(Integer val) {
            page = val;
            return this;
        }

        public Builder limit(Integer val) {
            limit = val;
            return this;
        }

        public Builder fields(Map<String, Object> val) {
            fields = val;
            return this;
        }

        public PageParam build() {
            return new PageParam(this);
        }
    }
}
