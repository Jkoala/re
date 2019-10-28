package cn.ljtnono.re.entity;

import java.io.Serializable;

/**
 * 角色权限关联表
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
public class ReRolePermission implements Serializable {

    private static final long serialVersionUID = 3352081800260051491L;

    /** 角色权限表id */
    private Integer id;

    /** 权限id */
    private Integer permissionId;

    /** 角色id */
    private Integer roleId;

    public ReRolePermission() {
    }

    private ReRolePermission(Builder builder) {
        setId(builder.id);
        setPermissionId(builder.permissionId);
        setRoleId(builder.roleId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReRolePermission copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.permissionId = copy.getPermissionId();
        builder.roleId = copy.getRoleId();
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public static final class Builder {
        private Integer id;
        private Integer permissionId;
        private Integer roleId;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder permissionId(Integer val) {
            permissionId = val;
            return this;
        }

        public Builder roleId(Integer val) {
            roleId = val;
            return this;
        }

        public ReRolePermission build() {
            return new ReRolePermission(this);
        }
    }
}
