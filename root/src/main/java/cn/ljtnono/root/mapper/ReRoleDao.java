package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReRole;
import cn.ljtnono.root.entity.ReRoleExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRoleDao {
    long countByExample(ReRoleExample example);

    int deleteByExample(ReRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReRole record);

    int insertSelective(ReRole record);

    List<ReRole> selectByExample(ReRoleExample example);

    ReRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReRole record, @Param("example") ReRoleExample example);

    int updateByExample(@Param("record") ReRole record, @Param("example") ReRoleExample example);

    int updateByPrimaryKeySelective(ReRole record);

    int updateByPrimaryKey(ReRole record);
}