package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReBookType;
import com.ljtnono.root.entity.ReBookTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReBookTypeDao {
    long countByExample(ReBookTypeExample example);

    int deleteByExample(ReBookTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReBookType record);

    int insertSelective(ReBookType record);

    List<ReBookType> selectByExample(ReBookTypeExample example);

    ReBookType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReBookType record, @Param("example") ReBookTypeExample example);

    int updateByExample(@Param("record") ReBookType record, @Param("example") ReBookTypeExample example);

    int updateByPrimaryKeySelective(ReBookType record);

    int updateByPrimaryKey(ReBookType record);
}