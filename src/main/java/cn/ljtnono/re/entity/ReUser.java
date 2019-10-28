package cn.ljtnono.re.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户实体对象
 *  @author 凌家童
 *  @date 2019/10/6
 *  @version 1.0
 *
*/
public class ReUser implements Serializable {

    private static final long serialVersionUID = 5314772925085498518L;

    /** 用户id */
    private Integer id;

    /** 用户名 */
    private String username;

    /** 用户密码 */
    @TableField("`password`")
    private String password;

    /** 用户qq */
    private String qq;

    /** 用户电话 */
    private String tel;

    /** 用户邮箱 */
    private String email;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date modifyTime;

    /** 是否删除 0删除 1正常 */
    @TableField("`delete`")
    private byte delete;

    private ReUser(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setPassword(builder.password);
        setQq(builder.qq);
        setTel(builder.tel);
        setEmail(builder.email);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDelete(builder.delete);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReUser copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.username = copy.getUsername();
        builder.password = copy.getPassword();
        builder.qq = copy.getQq();
        builder.tel = copy.getTel();
        builder.email = copy.getEmail();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ReUser() {
    }

    public static final class Builder {
        private Integer id;
        private String username;
        private String password;
        private String qq;
        private String tel;
        private String email;
        private Date createTime;
        private Date modifyTime;
        private byte delete;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder qq(String val) {
            qq = val;
            return this;
        }

        public Builder tel(String val) {
            tel = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
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

        public ReUser build() {
            return new ReUser(this);
        }
    }
}
