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
 * 用户角色关系实体
 * @author ljt
 * @date 2019/10/28
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReUserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4585895186916661368L;

    /** 用户角色表id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 角色id */
    private Integer roleId;
}
