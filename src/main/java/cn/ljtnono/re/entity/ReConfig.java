package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 配置类
 * @author ljt
 * @date 2019/11/9
 * @version 1.0
 */
public class ReConfig implements Serializable {

    private static final long serialVersionUID = -8489311168805539341L;
    /** 配置项id  */
    private Integer id;

    /** 配置项key  */
    @TableField("`key`")
    private String key;

    /** 配置项value  */
    @TableField("`value`")
    private String value;

    /** 配置项创建时间 */
    private Date createTime;

    /** 配置项最后修改时间 */
    private Date modifyTime;

    /** 配置项是否删除 0删除 1正常*/
    @TableField("`delete`")
    private byte delete;

    public ReConfig() {
    }

    private ReConfig(Builder builder) {
        setId(builder.id);
        setKey(builder.key);
        setValue(builder.value);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReConfig copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.key = copy.getKey();
        builder.value = copy.getValue();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.delete = copy.getDelete();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public byte getDelete() {
        return delete;
    }

    public void setDelete(byte delete) {
        this.delete = delete;
    }


    public static final class Builder {
        private Integer id;
        private String key;
        private String value;
        private Date createTime;
        private Date modifyTime;
        private byte delete;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder key(String val) {
            key = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder modifyTime(Date val) {
            modifyTime = val;
            return this;
        }

        public Builder delete(byte val) {
            delete = val;
            return this;
        }

        public ReConfig build() {
            return new ReConfig(this);
        }
    }
}
