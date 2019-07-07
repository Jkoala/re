package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.RePermission;
import com.ljtnono.root.entity.RePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RePermissionDao {
    long countByExample(RePermissionExample example);

    int deleteByExample(RePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RePermission record);

    int insertSelective(RePermission record);

    List<RePermission> selectByExample(RePermissionExample example);

    RePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RePermission record, @Param("example") RePermissionExample example);

    int updateByExample(@Param("record") RePermission record, @Param("example") RePermissionExample example);

    int updateByPrimaryKeySelective(RePermission record);

    int updateByPrimaryKey(RePermission record);
}