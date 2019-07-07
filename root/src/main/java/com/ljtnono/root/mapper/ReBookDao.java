package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReBook;
import com.ljtnono.root.entity.ReBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReBookDao {
    long countByExample(ReBookExample example);

    int deleteByExample(ReBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReBook record);

    int insertSelective(ReBook record);

    List<ReBook> selectByExample(ReBookExample example);

    ReBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReBook record, @Param("example") ReBookExample example);

    int updateByExample(@Param("record") ReBook record, @Param("example") ReBookExample example);

    int updateByPrimaryKeySelective(ReBook record);

    int updateByPrimaryKey(ReBook record);
}