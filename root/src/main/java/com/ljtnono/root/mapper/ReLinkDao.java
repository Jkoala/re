package com.ljtnono.root.mapper;

import com.ljtnono.root.entity.ReLink;
import com.ljtnono.root.entity.ReLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReLinkDao {
    long countByExample(ReLinkExample example);

    int deleteByExample(ReLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReLink record);

    int insertSelective(ReLink record);

    List<ReLink> selectByExample(ReLinkExample example);

    ReLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReLink record, @Param("example") ReLinkExample example);

    int updateByExample(@Param("record") ReLink record, @Param("example") ReLinkExample example);

    int updateByPrimaryKeySelective(ReLink record);

    int updateByPrimaryKey(ReLink record);
}