package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体类
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class RePermission implements Serializable {

    private static final long serialVersionUID = -5664674075785947553L;

    /** 权限id */
    private Integer id;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 是否删除 */
    @TableField("`delete`")
    private byte delete;

    /** 权限描述 */
    private String description;

    /** 权限访问路径 */
    private String res;

    public RePermission() {
    }

    private RePermission(Builder builder) {
        setId(builder.id);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
        setDescription(builder.description);
        setRes(builder.res);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(RePermission copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.delete = copy.getDelete();
        builder.description = copy.getDescription();
        builder.res = copy.getRes();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }


    public static final class Builder {
        private Integer id;
        private Date createTime;
        private Date modifyTime;
        private byte delete;
        private String description;
        private String res;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
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

        public Builder res(String val) {
            res = val;
            return this;
        }

        public RePermission build() {
            return new RePermission(this);
        }
    }
}
