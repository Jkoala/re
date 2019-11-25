package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍类型实体类
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReBookType implements Serializable {

    private static final long serialVersionUID = -2561242836029170847L;

    /** 书籍类型id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 是否删除 0表示删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 书籍类型的名字 */
    private String name;

    /** 书籍类型描述 */
    private String description;

    public ReBookType() {
    }

    private ReBookType(Builder builder) {
        setId(builder.id);
        setDelete(builder.delete);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setName(builder.name);
        setDescription(builder.description);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReBookType copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.delete = copy.getDelete();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.name = copy.getName();
        builder.description = copy.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final class Builder {
        private Integer id;
        private byte delete;
        private Date createTime;
        private Date modifyTime;
        private String name;
        private String description;

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

        public Builder description(String val) {
            description = val;
            return this;
        }

        public ReBookType build() {
            return new ReBookType(this);
        }
    }
}
