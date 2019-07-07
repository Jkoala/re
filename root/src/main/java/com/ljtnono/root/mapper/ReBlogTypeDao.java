package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReBlogType;
import com.ljtnono.root.entity.ReBlogTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReBlogTypeDao {
    long countByExample(ReBlogTypeExample example);

    int deleteByExample(ReBlogTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReBlogType record);

    int insertSelective(ReBlogType record);

    List<ReBlogType> selectByExample(ReBlogTypeExample example);

    ReBlogType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReBlogType record, @Param("example") ReBlogTypeExample example);

    int updateByExample(@Param("record") ReBlogType record, @Param("example") ReBlogTypeExample example);

    int updateByPrimaryKeySelective(ReBlogType record);

    int updateByPrimaryKey(ReBlogType record);
}