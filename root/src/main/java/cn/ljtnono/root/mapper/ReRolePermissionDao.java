package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReRolePermission;
import cn.ljtnono.root.entity.ReRolePermissionExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRolePermissionDao {
    long countByExample(ReRolePermissionExample example);

    int deleteByExample(ReRolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReRolePermission record);

    int insertSelective(ReRolePermission record);

    List<ReRolePermission> selectByExample(ReRolePermissionExample example);

    ReRolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReRolePermission record, @Param("example") ReRolePermissionExample example);

    int updateByExample(@Param("record") ReRolePermission record, @Param("example") ReRolePermissionExample example);

    int updateByPrimaryKeySelective(ReRolePermission record);

    int updateByPrimaryKey(ReRolePermission record);
}