package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的技能实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReSkill implements Serializable {

    private static final long serialVersionUID = -5222819912129815867L;

    /** 技能id */
    private Integer id;

    /** 技能名字 */
    private String name;

    /** 技能的所有者 */
    private String owner;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    /** 技能得分 */
    private Integer percent;

    public ReSkill() {
    }

    private ReSkill(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setOwner(builder.owner);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
        setPercent(builder.percent);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReSkill copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.name = copy.getName();
        builder.owner = copy.getOwner();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.delete = copy.getDelete();
        builder.percent = copy.getPercent();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String owner;
        private Date createTime;
        private Date modifyTime;
        private byte delete;
        private Integer percent;

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

        public Builder owner(String val) {
            owner = val;
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

        public Builder percent(Integer val) {
            percent = val;
            return this;
        }

        public ReSkill build() {
            return new ReSkill(this);
        }
    }
}
