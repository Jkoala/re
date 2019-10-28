package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReRole implements Serializable {

    private static final long serialVersionUID = 6155560455596066680L;

    /** 角色id */
    private Integer id;

    /** 角色名 */
    private String name;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    /** 角色描述 */
    private String description;

    public ReRole() {
    }

    private ReRole(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
        setDescription(builder.description);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReRole copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.name = copy.getName();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.delete = copy.getDelete();
        builder.description = copy.getDescription();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private Date createTime;
        private Date modifyTime;
        private byte delete;
        private String description;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
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

        public Builder description(String val) {
            description = val;
            return this;
        }

        public ReRole build() {
            return new ReRole(this);
        }
    }
}
