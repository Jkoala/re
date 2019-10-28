package cn.ljtnono.re.entity;

import java.io.Serializable;

/**
 * 用户角色关系实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReUserRole implements Serializable {

    private static final long serialVersionUID = 4585895186916661368L;

    /** 用户角色表id */
    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 角色id */
    private Integer roleId;

    public ReUserRole() {
    }

    private ReUserRole(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setRoleId(builder.roleId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReUserRole copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.userId = copy.getUserId();
        builder.roleId = copy.getRoleId();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public static final class Builder {
        private Integer id;
        private Integer userId;
        private Integer roleId;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public Builder roleId(Integer val) {
            roleId = val;
            return this;
        }

        public ReUserRole build() {
            return new ReUserRole(this);
        }
    }
}
