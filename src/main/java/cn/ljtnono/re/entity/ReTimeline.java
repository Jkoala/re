package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间轴实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReTimeline implements Serializable {


    private static final long serialVersionUID = -3919934196588131827L;
    /** 技能id */
    private Integer id;

    /** 时间轴内容 */
    private String content;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    public ReTimeline() {
    }

    private ReTimeline(Builder builder) {
        setId(builder.id);
        setContent(builder.content);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReTimeline copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.content = copy.getContent();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        private String content;
        private Date createTime;
        private Date modifyTime;
        private byte delete;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
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

        public ReTimeline build() {
            return new ReTimeline(this);
        }
    }
}
