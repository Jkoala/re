package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReUserRole;
import com.ljtnono.root.entity.ReUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReUserRoleDao {
    long countByExample(ReUserRoleExample example);

    int deleteByExample(ReUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReUserRole record);

    int insertSelective(ReUserRole record);

    List<ReUserRole> selectByExample(ReUserRoleExample example);

    ReUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReUserRole record, @Param("example") ReUserRoleExample example);

    int updateByExample(@Param("record") ReUserRole record, @Param("example") ReUserRoleExample example);

    int updateByPrimaryKeySelective(ReUserRole record);

    int updateByPrimaryKey(ReUserRole record);
}