package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储图片信息的实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReImage implements Serializable {

    private static final long serialVersionUID = -5419098997286534575L;

    /** 图片存储的id */
    private String id;

    /** 图片的文件名 */
    private String originName;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 图片大小 B为单位*/
    private Long size;

    /** 图片的类型，主要是后缀名 */
    private String type;

    /** 图片的访问url */
    private String url;

    /** 图片的上传者 */
    private String owner;

    public ReImage() {
    }

    private ReImage(Builder builder) {
        setId(builder.id);
        setOriginName(builder.originName);
        setDelete(builder.delete);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setSize(builder.size);
        setType(builder.type);
        setUrl(builder.url);
        setOwner(builder.owner);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReImage copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.originName = copy.getOriginName();
        builder.delete = copy.getDelete();
        builder.createTime = copy.getCreateTime();
        builder.modifyTime = copy.getModifyTime();
        builder.size = copy.getSize();
        builder.type = copy.getType();
        builder.url = copy.getUrl();
        builder.owner = copy.getOwner();
        return builder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public static final class Builder {
        private String id;
        private String originName;
        private byte delete;
        private Date createTime;
        private Date modifyTime;
        private Long size;
        private String type;
        private String url;
        private String owner;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder originName(String val) {
            originName = val;
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

        public Builder size(Long val) {
            size = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder owner(String val) {
            owner = val;
            return this;
        }

        public ReImage build() {
            return new ReImage(this);
        }
    }
}
