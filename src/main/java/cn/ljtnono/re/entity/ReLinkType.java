package cn.ljtnono.re.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 链接类型实体类
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReLinkType implements Serializable {

    private static final long serialVersionUID = -5481376664245861120L;

    /** 链接类型id */
    private Integer id;

    /** 是否删除 0删除 1正常 */
    private byte delete;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 链接类型的名称 */
    private String name;

    public ReLinkType() {
    }

    private ReLinkType(Builder builder) {
        setId(builder.id);
        setDelete(builder.delete);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReLinkType copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.delete = copy.getDelete();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.name = copy.getName();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getDelete() {
        return delete;
    }

    public void setDelete(byte delete) {
        this.delete = delete;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private Integer id;
        private byte delete;
        private Date createTime;
        private Date modifyTime;
        private String name;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder delete(byte val) {
            delete = val;
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

        public Builder name(String val) {
            name = val;
            return this;
        }

        public ReLinkType build() {
            return new ReLinkType(this);
        }
    }
}
