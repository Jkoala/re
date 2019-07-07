package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReUser;
import com.ljtnono.root.entity.ReUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReUserDao {
    long countByExample(ReUserExample example);

    int deleteByExample(ReUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReUser record);

    int insertSelective(ReUser record);

    List<ReUser> selectByExample(ReUserExample example);

    ReUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReUser record, @Param("example") ReUserExample example);

    int updateByExample(@Param("record") ReUser record, @Param("example") ReUserExample example);

    int updateByPrimaryKeySelective(ReUser record);

    int updateByPrimaryKey(ReUser record);
}