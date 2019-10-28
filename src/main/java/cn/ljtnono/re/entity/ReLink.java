package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客链接实体类
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReLink implements Serializable {

    private static final long serialVersionUID = 2713285364411129741L;

    /** 链接id */
    private Integer id;

    /** 链接url */
    private String url;

    /** 链接名 */
    private String name;

    /** 链接类型 */
    private String type;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    public ReLink() {
    }

    private ReLink(Builder builder) {
        setId(builder.id);
        setUrl(builder.url);
        setName(builder.name);
        setType(builder.type);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReLink copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.url = copy.getUrl();
        builder.name = copy.getName();
        builder.type = copy.getType();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        private String url;
        private String name;
        private String type;
        private Date createTime;
        private Date modifyTime;
        private byte delete;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
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

        public ReLink build() {
            return new ReLink(this);
        }
    }
}
