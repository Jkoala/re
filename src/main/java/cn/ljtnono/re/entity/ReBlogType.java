package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 *  博客类型实体
 *  @author 凌家童
 *  @date 2019/10/6
 *  @version 1.0
 *
*/
public class ReBlogType implements Serializable {

    private static final long serialVersionUID = -1997697590986661638L;

    /** 博客类型id */
    private Integer id;

    /** 是否删除 0删除 1正常*/
    @TableField("`delete`")
    private byte delete;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 创建时间 */
    private Date createTime;

    /** 博客类型名 */
    private String name;

    /** 博客类型描述 */
    private String description;

    public ReBlogType() {
    }

    private ReBlogType(Builder builder) {
        setId(builder.id);
        setDelete(builder.delete);
        setModifyTime(builder.modifyTime);
        setCreateTime(builder.createTime);
        setName(builder.name);
        setDescription(builder.description);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReBlogType copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.delete = copy.getDelete();
        builder.modifyTime = copy.getModifyTime();
        builder.createTime = copy.getCreateTime();
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        private Date modifyTime;
        private Date createTime;
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

        public Builder modifyTime(Date val) {
            modifyTime = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
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

        public ReBlogType build() {
            return new ReBlogType(this);
        }
    }

    @Override
    public String toString() {
        return "ReBlogType{" +
                "id=" + id +
                ", delete=" + delete +
                ", modifyTime=" + modifyTime +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
