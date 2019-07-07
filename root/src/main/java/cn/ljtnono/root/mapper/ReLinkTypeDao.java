package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReLinkType;
import cn.ljtnono.root.entity.ReLinkTypeExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReLinkTypeDao {
    long countByExample(ReLinkTypeExample example);

    int deleteByExample(ReLinkTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReLinkType record);

    int insertSelective(ReLinkType record);

    List<ReLinkType> selectByExample(ReLinkTypeExample example);

    ReLinkType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReLinkType record, @Param("example") ReLinkTypeExample example);

    int updateByExample(@Param("record") ReLinkType record, @Param("example") ReLinkTypeExample example);

    int updateByPrimaryKeySelective(ReLinkType record);

    int updateByPrimaryKey(ReLinkType record);
}