package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客实体类
 * @author ljt
 * @date 2019/10/10
 * @version 1.0
 */
public class ReBlog implements Serializable {

    private static final long serialVersionUID = -3699612037615768897L;

    /** 博客的id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 博客的标题 */
    private String title;

    /** 博客的作者 */
    private String author;

    /** 博客的类型 */
    private String type;

    /** 博客的摘要信息 */
    private String summary;

    /** 博客创建时间 */
    private Date createTime;

    /** 博客最后修改时间 */
    private Date modifyTime;

    /** 博客是否删除 0删除 1正常*/
    @TableField("`delete`")
    private byte delete;

    /** 博客的markdown */
    private String contentMarkdown;

    /** 博客的html */
    private String contentHtml;

    /** 博客的封面图片url */
    private String coverImage;

    /** 博客的评论数 */
    private int comment;

    /** 博客的浏览量 */
    private int view;

    public ReBlog() {
    }

    private ReBlog(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setAuthor(builder.author);
        setType(builder.type);
        setSummary(builder.summary);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
        setContentMarkdown(builder.contentMarkdown);
        setContentHtml(builder.contentHtml);
        setCoverImage(builder.coverImage);
        setComment(builder.comment);
        setView(builder.view);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReBlog copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.author = copy.getAuthor();
        builder.type = copy.getType();
        builder.summary = copy.getSummary();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.delete = copy.getDelete();
        builder.contentMarkdown = copy.getContentMarkdown();
        builder.contentHtml = copy.getContentHtml();
        builder.coverImage = copy.getCoverImage();
        builder.comment = copy.getComment();
        builder.view = copy.getView();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getContentMarkdown() {
        return contentMarkdown;
    }

    public void setContentMarkdown(String contentMarkdown) {
        this.contentMarkdown = contentMarkdown;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }


    public static final class Builder {
        private Integer id;
        private String title;
        private String author;
        private String type;
        private String summary;
        private Date createTime;
        private Date modifyTime;
        private byte delete;
        private String contentMarkdown;
        private String contentHtml;
        private String coverImage;
        private int comment;
        private int view;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder author(String val) {
            author = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder summary(String val) {
            summary = val;
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

        public Builder contentMarkdown(String val) {
            contentMarkdown = val;
            return this;
        }

        public Builder contentHtml(String val) {
            contentHtml = val;
            return this;
        }

        public Builder coverImage(String val) {
            coverImage = val;
            return this;
        }

        public Builder comment(int val) {
            comment = val;
            return this;
        }

        public Builder view(int val) {
            view = val;
            return this;
        }

        public ReBlog build() {
            return new ReBlog(this);
        }
    }

    @Override
    public String toString() {
        return "ReBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", delete=" + delete +
                ", contentMarkdown='" + contentMarkdown + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", comment=" + comment +
                ", view=" + view +
                '}';
    }
}
