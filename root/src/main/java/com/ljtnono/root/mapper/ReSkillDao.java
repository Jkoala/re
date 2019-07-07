package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReSkill;
import com.ljtnono.root.entity.ReSkillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReSkillDao {
    long countByExample(ReSkillExample example);

    int deleteByExample(ReSkillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReSkill record);

    int insertSelective(ReSkill record);

    List<ReSkill> selectByExample(ReSkillExample example);

    ReSkill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReSkill record, @Param("example") ReSkillExample example);

    int updateByExample(@Param("record") ReSkill record, @Param("example") ReSkillExample example);

    int updateByPrimaryKeySelective(ReSkill record);

    int updateByPrimaryKey(ReSkill record);
}