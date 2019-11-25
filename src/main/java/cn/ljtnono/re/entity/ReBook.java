package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * 我看的书籍实体类
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReBook implements Serializable {
    private static final long serialVersionUID = -5908659796059572626L;

    /** 书籍的id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 书籍的名字 */
    private String name;

    /** 书籍的作者 */
    private String author;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 出版日期 */
    private Date publishTime;

    /** 封面图片id */
    private String image;

    /** 书籍的摘要信息 */
    private String summary;

    /** 是否删除 */
    @TableField("`delete`")
    private byte delete;

    /** 书籍描述 */
    private String description;

    /** 书籍分类 */
    private String type;

    public ReBook() {
    }

    private ReBook(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setAuthor(builder.author);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setPublishTime(builder.publishTime);
        setImage(builder.image);
        setSummary(builder.summary);
        setDelete(builder.delete);
        setDescription(builder.description);
        setType(builder.type);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReBook copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.name = copy.getName();
        builder.author = copy.getAuthor();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.publishTime = copy.getPublishTime();
        builder.image = copy.getImage();
        builder.summary = copy.getSummary();
        builder.delete = copy.getDelete();
        builder.description = copy.getDescription();
        builder.type = copy.getType();
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private String author;
        private Date createTime;
        private Date modifyTime;
        private Date publishTime;
        private String image;
        private String summary;
        private byte delete;
        private String description;
        private String type;

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

        public Builder author(String val) {
            author = val;
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

        public Builder publishTime(Date val) {
            publishTime = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder summary(String val) {
            summary = val;
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

        public Builder type(String val) {
            type = val;
            return this;
        }

        public ReBook build() {
            return new ReBook(this);
        }
    }
}
