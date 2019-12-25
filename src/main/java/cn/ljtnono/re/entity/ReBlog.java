package cn.ljtnono.re.entity;

import cn.ljtnono.re.entity.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客实体类
 * @author ljt
 * @date 2019/12/11
 * @version 1.1
 * 新增使用lombok简化代码
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReBlog extends BaseEntity implements Serializable {

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

    private ReBlog(Builder builder) {
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setStatus(builder.status);
        setId(builder.id);
        setTitle(builder.title);
        setAuthor(builder.author);
        setType(builder.type);
        setSummary(builder.summary);
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
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.status = copy.getStatus();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.author = copy.getAuthor();
        builder.type = copy.getType();
        builder.summary = copy.getSummary();
        builder.contentMarkdown = copy.getContentMarkdown();
        builder.contentHtml = copy.getContentHtml();
        builder.coverImage = copy.getCoverImage();
        builder.comment = copy.getComment();
        builder.view = copy.getView();
        return builder;
    }


    public static final class Builder {
        private Date createTime;
        private Date modifyTime;
        private byte status;
        private Integer id;
        private String title;
        private String author;
        private String type;
        private String summary;
        private String contentMarkdown;
        private String contentHtml;
        private String coverImage;
        private int comment;
        private int view;

        private Builder() {
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder modifyTime(Date val) {
            modifyTime = val;
            return this;
        }

        public Builder status(byte val) {
            status = val;
            return this;
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
}
