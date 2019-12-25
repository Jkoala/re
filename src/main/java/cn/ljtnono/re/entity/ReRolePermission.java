package cn.ljtnono.re.entity;

import cn.ljtnono.re.entity.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色权限关联表
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReRolePermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3352081800260051491L;

    /** 角色权限表id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 权限id */
    private Integer permissionId;

    /** 角色id */
    private Integer roleId;
}
